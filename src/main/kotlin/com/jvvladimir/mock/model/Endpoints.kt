package com.jvvladimir.mock.model

import kotlinx.serialization.Serializable

@Serializable
data class Endpoints(
    val endpoints: List<Endpoint>
)
