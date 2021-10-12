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
}
