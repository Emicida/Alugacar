package com.example.alugacar.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MotoristaDAO {
    @Insert
    suspend fun insert(motorista: MotoristaEntity): Long

    @Update
    suspend fun update(motorista: MotoristaEntity)

    @Query("DELETE FROM motorista WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("DELETE FROM motorista")
    suspend fun delteAll()

    @Query("SELECT * FROM motorista")
    suspend fun getAll(): List<MotoristaEntity>
}