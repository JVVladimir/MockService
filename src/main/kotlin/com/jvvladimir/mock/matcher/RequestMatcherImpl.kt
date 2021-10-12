package com.jvvladimir.mock.matcher

import com.jvvladimir.mock.model.Endpoint
import com.jvvladimir.mock.model.MethodType
import com.jvvladimir.mock.store.ConfigHolder
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest

@Component
class RequestMatcherImpl(
    val configHolder: ConfigHolder
) : RequestMatcher {

    override fun match(request: HttpServletRequest): Endpoint? {
        val uri = request.requestURI
        val method = request.method

        val endpoint = configHolder.config.endpoints.firstOrNull {
            it.request.url == uri && it.request.method == MethodType.valueOf(method)
        }

        return endpoint
    }

}