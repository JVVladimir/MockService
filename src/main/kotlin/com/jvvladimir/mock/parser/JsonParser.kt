package com.jvvladimir.mock.parser

import com.jvvladimir.mock.model.Endpoints
import org.springframework.stereotype.Component
import java.nio.file.Path

@Component
class JsonParser : Parser {

    override fun decode(path: Path): Endpoints {
        TODO("Not yet implemented")
    }

    override fun encode(endpoints: Endpoints): String {
        TODO("Not yet implemented")
    }
}