package com.example.alugacar.repository

import androidx.lifecycle.LiveData
import com.example.alugacar.db.MotoristaEntity

interface MotoristaRepository {
    suspend fun insertMotorista(name: String, email: String, phone: String, adress: String, describe: String): Long
    suspend fun updateMotorista(id: Long, name: String, email: String, phone: String, adress: String, describe: String)
    suspend fun deleteMotorista(id: Long)
    suspend fun deleteAllMotoristas()
    suspend fun getAllMotoristas(): List<MotoristaEntity>
}