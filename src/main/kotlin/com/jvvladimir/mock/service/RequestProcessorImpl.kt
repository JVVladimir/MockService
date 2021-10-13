package com.jvvladimir.mock.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.jvvladimir.mock.matcher.RequestMatcher
import com.jvvladimir.mock.model.Response
import com.jvvladimir.mock.parser.MillisecondsParser
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono

@Service
class RequestProcessorImpl(
    val requestMatcher: RequestMatcher,
    val parser: MillisecondsParser,
    val objectMapper: ObjectMapper
) : RequestProcessor {

    companion object {
        private val log = KotlinLogging.logger {}
    }

    override fun process(request: ServerHttpRequest, response: ServerHttpResponse): Mono<Any> {
        log.debug { "Process request: $request" }
        val endpoint = requestMatcher.match(request)
            ?: throw ResponseStatusException(HttpStatus.FORBIDDEN, "There are no description request in config file")

        val endpointResponse = endpoint.response

        waitBusy(endpointResponse)

        if (endpointResponse == null) {
            return Mono.empty()
        }

        if (endpointResponse.errorCode != null) {
            throw ResponseStatusException(HttpStatus.valueOf(endpointResponse.errorCode), endpointResponse.errorMessage)
        }

        if (endpointResponse.successCode != null) {
            response.statusCode = HttpStatus.valueOf(endpointResponse.successCode)
        }

        if (endpointResponse.headers != null) {
            endpointResponse.headers.entries.forEach {
                response.headers[it.key] = it.value
            }
        }

        return if (endpointResponse.body == null) {
            Mono.empty()
        } else {
            Mono.just(tryConvertToJson(endpointResponse.body))
        }
    }

    private fun tryConvertToJson(body: String): Any =
        try {
            objectMapper.readTree(body)
        } catch (ex: Exception) {
            body
        }

    private fun waitBusy(endpointResponse: Response?) {
        log.debug { "Busy waiting for endpointResponse: $endpointResponse" }
        if (endpointResponse?.delay != null) {
            Thread.sleep((parser.parseToMilliseconds(endpointResponse.delay)))
        }
    }
}