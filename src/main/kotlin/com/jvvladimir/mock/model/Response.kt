package com.jvvladimir.mock.model

data class Response(
    val successCode: Int? = null,
    val headers: Map<String, String>? = null,
    val body: String? = null,
    val errorCode: Int? = null,
    val errorMessage: String? = null,
    val delay: String? = null
)
