package com.jvvladimir.mock.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.springframework.http.HttpMethod

@SerialName("request")
@Serializable
data class Request(
    val uri: String,
    val method: HttpMethod
)
