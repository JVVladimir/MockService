package com.jvvladimir.mock.parser

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.jvvladimir.mock.model.Endpoints
import com.jvvladimir.mock.validation.UriValidator
import org.springframework.stereotype.Component

@Component
class JsonParser(
    private val uriValidator: UriValidator,
    private val jsonMapper: ObjectMapper
) : Parser {

    override fun decode(rawConfig: String): Endpoints {
        val config = jsonMapper.readValue<Endpoints>(rawConfig)
        config.endpoints.forEach {
            uriValidator.validate(it.request.uri)
        }
        return config
    }

}