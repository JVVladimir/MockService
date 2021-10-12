package com.jvvladimir.mock.parser

import com.jvvladimir.mock.validation.ValidationException
import org.springframework.stereotype.Component

@Component
class MillisecondsParser {

    companion object {
        private const val SEC = "s"
        private const val MSEC = "ms"
    }

    fun parseToMilliseconds(s: String): Long {
        return if (s.contains(SEC)) {
            s.substringBefore(SEC).toLong() * 1000
        } else if (s.contains(MSEC)) {
            s.substringBefore(MSEC).toLong()
        } else {
            throw ValidationException("Could not validate delay")
        }
    }
}