package com.jvvladimir.mock.model


data class Endpoint(
    val request: Request,
    val response: Response? = null
)
