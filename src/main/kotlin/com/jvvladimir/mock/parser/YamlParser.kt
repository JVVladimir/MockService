package com.jvvladimir.mock.parser

import com.fasterxml.jackson.databind.ObjectMapper
import com.jvvladimir.mock.model.Endpoints
import com.jvvladimir.mock.validation.UriValidator
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
class YamlParser(
    private val uriValidator: UriValidator,
    @Qualifier("yaml")
    private val yamlMapper: ObjectMapper
) : Parser {

    override fun decode(rawConfig: String): Endpoints {
        val config = yamlMapper.readValue(rawConfig, Endpoints::class.java)
        config.endpoints.forEach {
            uriValidator.validate(it.request.uri)
        }
        return config
    }

}