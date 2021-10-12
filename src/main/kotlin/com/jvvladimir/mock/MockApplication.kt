package com.jvvladimir.mock

import com.charleskorn.kaml.Yaml
import com.jvvladimir.mock.model.Endpoint
import com.jvvladimir.mock.model.Endpoints
import kotlinx.serialization.decodeFromString
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.nio.file.Files
import java.nio.file.Path

@SpringBootApplication
class MockApplication

fun main(args: Array<String>) {
    runApplication<MockApplication>(*args)

    val path = Files.readString(Path.of("/Users/a19439053/IdeaProjects/MockService/src/main/resources/config.yml"))
    val result = Yaml.default.decodeFromString<Endpoints>(path)

    println(result)
}
