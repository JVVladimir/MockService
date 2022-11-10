package com.jvvladimir.mock.model

import org.springframework.http.HttpMethod


data class Request(
    val uri: String,
    val method: HttpMethod
)
