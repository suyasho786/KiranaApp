package com.example.kiranaapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [KiranaItems::class], version = 1)
abstract class KiranaDataBase: RoomDatabase() {
    abstract fun getKiranDao(): KiranaAppDAO

    companion object {
        @Volatile
        private var instance: KiranaDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDataBase(context).also {
                instance = it
            }
        }

        private fun createDataBase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                KiranaDataBase::class.java,
                "Kirana.db"
            ).build()
    }
}