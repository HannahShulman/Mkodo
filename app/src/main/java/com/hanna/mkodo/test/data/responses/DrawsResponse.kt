package com.hanna.mkodo.test.data.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DrawsResponse(val draws: List<DrawResponse>)

@Serializable
data class DrawResponse(
    val id: String,
    val drawDate: String,
    val number1: String,
    val number2: String,
    val number3: String,
    val number4: String,
    val number5: String,
    val number6: String,
    @SerialName("bonus-ball") val bonusBall: String,
    val topPrize: Long
)