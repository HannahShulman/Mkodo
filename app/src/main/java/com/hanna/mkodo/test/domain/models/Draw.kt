package com.hanna.mkodo.test.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "draws")
data class Draw(
    @PrimaryKey var id: String = "0",
    var drawDate: String = "",
    var number1: String = "",
    var number2: String = "",
    var number3: String = "",
    var number4: String = "",
    var number5: String = "",
    var number6: String = "",
    var bonusBall: String = "",
    var topPrize: Long = 0//BigInteger
)