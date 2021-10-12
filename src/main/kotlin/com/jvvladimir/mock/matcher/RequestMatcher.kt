package com.jvvladimir.mock.matcher

import com.jvvladimir.mock.model.Endpoint
import com.jvvladimir.mock.model.MethodType
import com.jvvladimir.mock.store.ConfigHolder
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest

interface RequestMatcher {

    fun match(request: HttpServletRequest): Endpoint?
}