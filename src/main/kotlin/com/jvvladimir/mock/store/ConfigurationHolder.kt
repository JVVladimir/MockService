package com.jvvladimir.mock.store

import com.jvvladimir.mock.model.Endpoints
import com.jvvladimir.mock.parser.YamlParser
import org.springframework.stereotype.Component

@Component
class ConfigurationHolder(
    val parser: YamlParser
) {

    @Volatile
    private var config: Endpoints? = null

    fun storeConfig(rawConfig: String) {
        config = parser.decode(rawConfig)
    }

    fun getConfig(): Endpoints {
        if (config == null) {
            throw ConfigurationException("Configuration does not configured")
        }
        return config as Endpoints
    }
}