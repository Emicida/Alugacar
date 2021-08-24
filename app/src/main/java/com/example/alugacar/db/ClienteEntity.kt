package com.example.alugacar.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "cliente")
data class ClienteEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long=0,
    val name: String,
    val email: String,
    val phone: String,
    val adress: String,
    val car: String
) : Parcelable