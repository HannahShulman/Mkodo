package com.hanna.mkodo.test.data.datasource.network

import android.content.Context
import com.hanna.mkodo.test.data.responses.DrawsResponse
import kotlinx.serialization.json.Json

//in Real world, would use Retrofit/Ktor(kmm),
class Api(private val context: Context) {

    fun fetchDraws(): DrawsResponse {
        fun readJsonFromAssets(context: Context, fileName: String): String {
            return context.assets.open(fileName).bufferedReader().use { it.readText() }
        }
        //perhaps using moshi is also an option...
        return Json.decodeFromString<DrawsResponse>(readJsonFromAssets(context, "get_draws.json"))
    }
}