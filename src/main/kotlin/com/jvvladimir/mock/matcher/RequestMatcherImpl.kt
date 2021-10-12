package com.jvvladimir.mock.matcher

import com.jvvladimir.mock.model.Endpoint
import com.jvvladimir.mock.model.MethodType
import com.jvvladimir.mock.store.ConfigurationHolder
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest

@Component
class RequestMatcherImpl(
    val configHolder: ConfigurationHolder
) : RequestMatcher {

    companion object {
        const val PATTERN = "([\\w.,@?^=%&:/~+#-]*[\\w@?^=%&/~+#-])?"
    }

    override fun match(request: HttpServletRequest): Endpoint? {
        val uri = request.requestURI
        val method = request.method

        val endpoint = configHolder.getConfig().endpoints.firstOrNull {
            matchUri(it.request.uri, uri) && it.request.method == MethodType.valueOf(method)
        }

        return endpoint
    }

    private fun matchUri(expected: String, actual: String): Boolean {
        val uriPattern = expected.replace("{}", PATTERN)

        return actual.matches(Regex(uriPattern))
    }

}