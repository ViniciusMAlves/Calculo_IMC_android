package com.example.atividade1.dataroom

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(IMC::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun imcDao(): ImcDao
}
