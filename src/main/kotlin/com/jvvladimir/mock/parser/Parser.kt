package com.jvvladimir.mock.parser

import com.jvvladimir.mock.model.Endpoints
import java.nio.file.Path

interface Parser {

    fun decode(rawConfig: String): Endpoints

    fun encode(endpoints: Endpoints): String
}