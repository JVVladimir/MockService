package com.jvvladimir.mock.parser

import com.jvvladimir.mock.model.Endpoints

interface Parser {

    fun decode(rawConfig: String): Endpoints

}