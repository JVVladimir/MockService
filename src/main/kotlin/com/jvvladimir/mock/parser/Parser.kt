package com.jvvladimir.mock.parser

import com.jvvladimir.mock.model.Endpoints
import java.nio.file.Path

interface Parser {

    fun decode(path: Path): Endpoints

    fun encode(endpoints: Endpoints): String
}