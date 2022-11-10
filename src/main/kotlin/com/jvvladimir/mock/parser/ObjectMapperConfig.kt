package com.jvvladimir.mock.parser

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class ObjectMapperConfig {

    @Bean("json")
    @Primary
    fun jsonMapper(): ObjectMapper {
        val mapper = ObjectMapper()
        mapper.registerModule(KotlinModule())
        return mapper
    }

    @Bean("yaml")
    fun yamlMapper(): ObjectMapper {
        val mapper = ObjectMapper(YAMLFactory())
        mapper.registerModule(KotlinModule())
        return mapper
    }
}