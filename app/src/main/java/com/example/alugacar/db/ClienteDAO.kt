package com.example.alugacar.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ClienteDAO {
    @Insert
    suspend fun insert(cliente: ClienteEntity): Long

    @Update
    suspend fun update(cliente: ClienteEntity)

    @Query("DELETE FROM cliente WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("DELETE FROM cliente")
    suspend fun delteAll()

    @Query("SELECT * FROM cliente")
    suspend fun getAll(): List<MotoristaEntity>
}