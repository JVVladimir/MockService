package com.jvvladimir.mock.parser

import com.charleskorn.kaml.Yaml
import com.jvvladimir.mock.model.Endpoints
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import org.springframework.stereotype.Component
import java.nio.file.Files
import java.nio.file.Path

@Component
class YamlParser : Parser {

    override fun decode(path: Path): Endpoints {
        val yaml = Files.readString(path)
        return Yaml.default.decodeFromString(yaml)
    }

    override fun encode(endpoints: Endpoints) = Yaml.default.encodeToString(endpoints)
}