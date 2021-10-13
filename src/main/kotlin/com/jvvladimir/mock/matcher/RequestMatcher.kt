package com.jvvladimir.mock.matcher

import com.jvvladimir.mock.model.Endpoint
import org.springframework.http.server.reactive.ServerHttpRequest

interface RequestMatcher {

    fun match(request: ServerHttpRequest): Endpoint?
}