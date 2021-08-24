package com.example.alugacar.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
import java.security.AccessControlContext

@Database(entities = [MotoristaEntity::class], version = 1)
abstract class DB : RoomDatabase(){
    abstract val motoristaDAO: MotoristaDAO
    companion object{
        @Volatile
        private var INSTANCE: DB? = null
        @InternalCoroutinesApi
        fun getInstance(context: Context): DB{
            synchronized(this){
                var instance: DB? = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context,
                        DB::class.java,
                        "db"
                    ).build()
                }
                return instance
            }
        }
    }
}