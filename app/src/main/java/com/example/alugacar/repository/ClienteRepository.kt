package com.example.alugacar.repository

import androidx.lifecycle.LiveData
import com.example.alugacar.db.ClienteEntity

interface ClienteRepository {
    suspend fun insertCliente(name: String, email: String, phone: String, adress: String, car: String): Long
    suspend fun updateCliente(id: Long, name: String, email: String, phone: String, adress: String, car: String)
    suspend fun deleteCliente(id: Long)
    suspend fun deleteAllClientes()
    suspend fun getAllClientes(): List<ClienteEntity>
}