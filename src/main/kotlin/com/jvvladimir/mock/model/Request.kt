package com.jvvladimir.mock.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@SerialName("request")
@Serializable
data class Request(
    val uri: String,
    val method: MethodType
)
