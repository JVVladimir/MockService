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
            if ((bracketFound && i != '}') || (!bracketFound && i == '}')) {
                throw ValidationException("Brackets in URI validation failed")
            } else {
                bracketFound = false
            }
        }
    }
}