package com.jvvladimir.mock.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@SerialName("response")
@Serializable
data class Response(
    val headers: Map<String, String>? = null,
    val body: String? = null,
    val delay: String? = null // exception
)
