package com.hanna.mkodo.test.data.mappers

import com.hanna.mkodo.test.data.responses.DrawResponse
import com.hanna.mkodo.test.domain.models.Draw
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun DrawResponse.map(): Draw {

    fun formatDateString(inputDate: String): String {
        val inputFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormatter = SimpleDateFormat("dd MMMM, yyyy", Locale.getDefault())
        val date: Date = inputFormatter.parse(inputDate)!!
        return outputFormatter.format(date)
    }

    return Draw(
        id = this.id,
        drawDate = formatDateString(this.drawDate),
        number1 = this.number1,
        number2 = this.number2,
        number3 = this.number3,
        number4 = this.number4,
        number5 = this.number5,
        number6 = this.number6,
        bonusBall = this.bonusBall,
        topPrize = this.topPrize
    )
}

