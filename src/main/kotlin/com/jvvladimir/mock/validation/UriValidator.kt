package com.jvvladimir.mock.validation

import org.springframework.stereotype.Component

@Component
class UriValidator {

    fun validate(uri: String) {
        var bracketFound = false
        for (i in uri) {
            if (!bracketFound && i == '{') {
                bracketFound = true
                continue
            }
            if ((bracketFound && i != '}') || i == '}') {
                throw ValidationException()
            } else {
                bracketFound = false
            }
        }
    }
}