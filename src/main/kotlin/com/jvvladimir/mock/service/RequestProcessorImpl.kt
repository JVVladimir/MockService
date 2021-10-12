package com.jvvladimir.mock.service

import com.jvvladimir.mock.matcher.RequestMatcher
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Service
class RequestProcessorImpl(
    val requestMatcher: RequestMatcher
) : RequestProcessor {

    override fun process(request: HttpServletRequest, response: HttpServletResponse): HttpServletResponse {
        val endpoint = requestMatcher.match(request)

        if (endpoint == null) {
            response.sendError(HttpStatus.FORBIDDEN.value(), "There are no description request in config file")
            return response
        }

        val endpointResponse = endpoint.response

        if (endpointResponse?.delay != null) {
            Thread.sleep((endpointResponse.delay * 1000).toLong())
        }

        if (endpointResponse == null) {
            response.status = HttpServletResponse.SC_OK
            return response
        }

        if (endpointResponse.errorCode != null) {
            response.sendError(endpointResponse.errorCode, endpointResponse.errorMessage)
            return response
        }

        response.status = HttpServletResponse.SC_OK

        if (endpointResponse.headers != null) {
            endpointResponse.headers.entries.forEach {
                response.setHeader(it.key, it.value)
            }
        }

        if (endpointResponse.body != null) {
            response.writer.write(endpointResponse.body)
            response.writer.flush()
        }

        return response
    }
}