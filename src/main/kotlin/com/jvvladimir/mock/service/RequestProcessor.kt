package com.jvvladimir.mock.service

import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.http.server.reactive.ServerHttpResponse
import reactor.core.publisher.Mono

interface RequestProcessor {

    fun process(request: ServerHttpRequest, response: ServerHttpResponse): Mono<Any>
}