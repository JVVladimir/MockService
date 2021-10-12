package com.jvvladimir.mock.model

import kotlinx.serialization.Serializable

@Serializable
data class Endpoint(
    val request: Request,
    val response: Response? = null
)
