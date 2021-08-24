package com.example.alugacar.repository
import androidx.lifecycle.LiveData
import com.example.alugacar.db.MotoristaDAO
import com.example.alugacar.db.MotoristaEntity

class DatabaseDataSource(
    private val motoristaDAO: MotoristaDAO
): MotoristaRepository {
    override suspend fun insertMotorista(
        name: String,
        email: String,
        phone: String,
        adress: String,
        describe: String
    ): Long {
        val motorista = MotoristaEntity(
            name = name,
            email = email,
            phone = phone,
            adress = adress,
            describe = describe
        )
        return motoristaDAO.insert(motorista)
    }

    override suspend fun updateMotorista(
        id: Long,
        name: String,
        email: String,
        phone: String,
        adress: String,
        describe: String
    ) {
        val motorista = MotoristaEntity(
            id = id,
            name = name,
            email = email,
            phone = phone,
            adress = adress,
            describe = describe
        )
        motoristaDAO.update(motorista)
    }

    override suspend fun deleteMotorista(id: Long) {
        motoristaDAO.delete(id)
    }

    override suspend fun deleteAllMotoristas() {
        motoristaDAO.delteAll()
    }

    override suspend fun getAllMotoristas(): List<MotoristaEntity> {
        return motoristaDAO.getAll()
    }
}