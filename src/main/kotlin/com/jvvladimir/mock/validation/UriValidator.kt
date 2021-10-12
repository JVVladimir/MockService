package com.jvvladimir.mock.validation

import org.springframework.stereotype.Component

@Component
class UriValidator {

    fun validate(uri: String) {
        var bracketFound = false
        for (i in uri) {
            if (i == '{') {
                bracketFound = true
                continue
            }
            // todo: добавить регулярку на запрещенные символы
            if (bracketFound && i in listOf('{', '/')) {
                throw ValidationException()
            }
            if (i == '}') {
                bracketFound = false
            }
        }
    }
}