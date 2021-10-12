package com.jvvladimir.mock.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@SerialName("request")
@Serializable
data class Request(
    val url: String,
    val method: MethodType,
    val params: List<String>? = null,
    val headers: Map<String, String>? = null,
    val body: String? = null
)
