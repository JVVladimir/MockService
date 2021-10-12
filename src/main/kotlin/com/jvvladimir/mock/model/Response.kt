package com.jvvladimir.mock.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@SerialName("response")
@Serializable
data class Response(
    val headers: Map<String, String>? = null,
    val body: String? = null,
    val errorCode: Int? = null,
    val errorMessage: String? = null,
    val delay: Int? = null
)
