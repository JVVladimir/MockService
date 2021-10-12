package com.jvvladimir.mock.parser

import com.charleskorn.kaml.Yaml
import com.jvvladimir.mock.model.Endpoints
import com.jvvladimir.mock.validation.UriValidator
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import org.springframework.stereotype.Component
import java.nio.file.Files
import java.nio.file.Path

@Component
class YamlParser(
    val uriValidator: UriValidator
) : Parser {

    override fun decode(rawConfig: String): Endpoints {
        val config = Yaml.default.decodeFromString<Endpoints>(rawConfig)
        config.endpoints.forEach {
            uriValidator.validate(it.request.uri)
        }
        return config
    }

    override fun encode(endpoints: Endpoints) = Yaml.default.encodeToString(endpoints)
}