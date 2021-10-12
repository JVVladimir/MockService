package com.jvvladimir.mock.controller

import com.jvvladimir.mock.service.RequestProcessor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
class GatewayController(
    val requestProcessor: RequestProcessor
) {

    @RequestMapping("**")
    fun requestHandler(servletRequest: HttpServletRequest, servletResponse: HttpServletResponse): Mono<ResponseEntity<Any?>> {
        val response = requestProcessor.process(servletRequest, servletResponse)
        return if (response == null) {
            ResponseEntity.ok(Any()).toMono()
        } else {
            ResponseEntity.ok(response).toMono()
        }
    }
}