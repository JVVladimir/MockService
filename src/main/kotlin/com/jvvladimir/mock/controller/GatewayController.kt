package com.jvvladimir.mock.controller

import com.jvvladimir.mock.service.RequestProcessor
import com.jvvladimir.mock.store.ConfigurationHolder
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono


@RestController
class GatewayController(
    val requestProcessor: RequestProcessor,
    val configHolder: ConfigurationHolder
) {

    @RequestMapping("**")
    fun requestHandler(
        exchange: ServerWebExchange
    ): Mono<Any> {
        return requestProcessor.process(exchange.request, exchange.response)
    }

    @PostMapping("\${server.defaultConfigPath}")
    fun addConfig(@RequestBody config: String) {
        configHolder.storeConfig(config)
    }
}