package com.jvvladimir.mock.matcher

import com.jvvladimir.mock.model.Endpoint
import javax.servlet.http.HttpServletRequest

interface RequestMatcher {

    fun match(request: HttpServletRequest): Endpoint?
}