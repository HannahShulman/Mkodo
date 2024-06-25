package com.hanna.mkodo.test.data.repositories

import com.hanna.mkodo.test.data.datasource.db.DrawDao
import com.hanna.mkodo.test.data.mappers.map
import com.hanna.mkodo.test.data.datasource.network.Api
import com.hanna.mkodo.test.domain.models.Draw

class DrawsRepository(private val api: Api, private val drawDao: DrawDao) {

    suspend fun fetchDraws() = runCatching {
        val networkData = api.fetchDraws().draws.map { it.map() }//fetch from network
        println("MESSAGE::: ${networkData.size}")
        drawDao.insertDraws(networkData)
        drawDao.getAllDraws()//source of truth
    }

    suspend fun fetchDrawById(id: String): Result<Draw> = runCatching {
        drawDao.getDrawById(id)
    }
}