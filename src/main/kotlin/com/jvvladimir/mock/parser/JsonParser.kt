package com.jvvladimir.mock.parser

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.jvvladimir.mock.model.Endpoints
import com.jvvladimir.mock.validation.UriValidator
import org.springframework.stereotype.Component

@Component
class JsonParser(
    val uriValidator: UriValidator,
    val objectMapper: ObjectMapper
) : Parser {

    override fun decode(rawConfig: String): Endpoints {
        val config = objectMapper.readValue<Endpoints>(rawConfig)
        config.endpoints.forEach {
            uriValidator.validate(it.request.uri)
        }
        return config
    }

    override fun encode(endpoints: Endpoints): String = objectMapper.writeValueAsString(endpoints)
}