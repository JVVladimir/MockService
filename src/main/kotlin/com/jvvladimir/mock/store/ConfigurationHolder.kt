package com.jvvladimir.mock.store

import com.jvvladimir.mock.model.Endpoints
import com.jvvladimir.mock.parser.YamlParser
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class ConfigurationHolder(
    private val parser: YamlParser
) {

    companion object {
        private val log = KotlinLogging.logger {}
    }

    @Volatile
    private var config: Endpoints? = null

    fun storeConfig(rawConfig: String) {
        log.debug { "Uploading config file: $rawConfig" }
        config = parser.decode(rawConfig)

        log.debug { "Configuration uploaded" }
    }

    fun getConfig(): Endpoints {
        if (config == null) {
            throw ConfigurationException("Configuration does not configured")
        }
        return config as Endpoints
    }
}