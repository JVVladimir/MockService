package com.jvvladimir.mock.controller

import com.jvvladimir.mock.service.RequestProcessor
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
class GatewayController(
    val requestProcessor: RequestProcessor
) {

    @RequestMapping("**")
    fun requestHandler(request: HttpServletRequest, response: HttpServletResponse): Mono<HttpServletResponse> {
        return Mono.just(requestProcessor.process(request, response))
    }
}