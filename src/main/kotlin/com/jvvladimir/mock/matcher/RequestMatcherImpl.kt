package com.jvvladimir.mock.matcher

import com.jvvladimir.mock.model.Endpoint
import com.jvvladimir.mock.store.ConfigurationHolder
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.stereotype.Component

@Component
class RequestMatcherImpl(
    val configHolder: ConfigurationHolder
) : RequestMatcher {

    companion object {
        const val PATTERN = "[a-zA-Z0-9_\\-.~%]+"
    }

    override fun match(request: ServerHttpRequest): Endpoint? {
        val uri = request.uri.path
        val method = request.method

        val endpoint = configHolder.getConfig().endpoints.firstOrNull {
            matchUri(it.request.uri, uri) && it.request.method == method
        }

        return endpoint
    }

    private fun matchUri(expected: String, actual: String): Boolean {
        val uriPattern = expected.replace("{}", PATTERN)

        return actual.matches(Regex(uriPattern))
    }

}