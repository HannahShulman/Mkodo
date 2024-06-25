package com.hanna.mkodo.test.data.datasource.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hanna.mkodo.test.domain.models.Draw

@Dao
interface DrawDao {
    @Query("SELECT * FROM draws")
    suspend fun getAllDraws(): List<Draw>
    @Query("SELECT * FROM draws WHERE id=:id")
    suspend fun getDrawById(id: String): Draw
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDraws(draw: List<Draw>) // Return type changed to Long
}