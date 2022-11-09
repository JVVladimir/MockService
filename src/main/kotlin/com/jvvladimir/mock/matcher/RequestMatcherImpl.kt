package com.jvvladimir.mock.matcher

import com.jvvladimir.mock.model.Endpoint
import com.jvvladimir.mock.service.RequestProcessorImpl
import com.jvvladimir.mock.store.ConfigurationHolder
import mu.KotlinLogging
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.stereotype.Component

@Component
class RequestMatcherImpl(
    private val configHolder: ConfigurationHolder
) : RequestMatcher {

    companion object {
        private const val PATTERN = "[a-zA-Z0-9_\\-.~%]+"
        private val log = KotlinLogging.logger {}
    }

    override fun match(request: ServerHttpRequest): Endpoint? {
        val uri = request.uri.path
        val method = request.method
        log.debug { "Request method and uri: $method, $uri" }

        val endpoint = configHolder.getConfig().endpoints.firstOrNull {
            matchUri(it.request.uri, uri) && it.request.method == method
        }

        return endpoint
    }

    private fun matchUri(expected: String, actual: String): Boolean {
        log.debug { "Matching two urls: $expected, $actual" }
        val uriPattern = expected.replace("{}", PATTERN)

        return actual.matches(Regex(uriPattern))
    }

}