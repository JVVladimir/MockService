package com.jvvladimir.mock.store

import com.jvvladimir.mock.model.Endpoints
import com.jvvladimir.mock.parser.YamlParser
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component
import java.nio.file.Path
import kotlin.system.exitProcess

@Component
class ConfigHolder(
    val parser: YamlParser
): ApplicationListener<ApplicationReadyEvent> {

    lateinit var config: Endpoints

    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        try {
            config =
                parser.decode(Path.of("/Users/a19439053/IdeaProjects/MockService/src/main/resources/config.yml"))
        } catch (ex: Exception) {
            println(ex)
            exitProcess(-1)
        }
    }
}