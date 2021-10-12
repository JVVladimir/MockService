package com.jvvladimir.mock.service

import com.jvvladimir.mock.matcher.RequestMatcher
import com.jvvladimir.mock.model.Response
import com.jvvladimir.mock.parser.MillisecondsParser
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Service
class RequestProcessorImpl(
    val requestMatcher: RequestMatcher,
    val parser: MillisecondsParser
) : RequestProcessor {

    override fun process(request: HttpServletRequest, response: HttpServletResponse): Any? {
        val endpoint = requestMatcher.match(request)

        if (endpoint == null) {
            response.sendError(HttpStatus.FORBIDDEN.value(), "There are no description request in config file")
            return null
        }

        val endpointResponse = endpoint.response

        waitBusy(endpointResponse)

        if (endpointResponse == null) {
            response.status = HttpServletResponse.SC_OK
            return null
        }

        if (endpointResponse.errorCode != null) {
            response.sendError(endpointResponse.errorCode, endpointResponse.errorMessage)
            return null
        }

        response.status = HttpServletResponse.SC_OK

        if (endpointResponse.headers != null) {
            endpointResponse.headers.entries.forEach {
                response.setHeader(it.key, it.value)
            }
        }

        if (endpointResponse.body != null) {
            return endpointResponse.body
        }

        return null
    }

    private fun waitBusy(endpointResponse: Response?) {
        if (endpointResponse?.delay != null) {
            Thread.sleep((parser.parseToMilliseconds(endpointResponse.delay)))
        }
    }
}