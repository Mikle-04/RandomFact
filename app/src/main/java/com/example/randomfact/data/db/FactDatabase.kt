package com.example.randomfact.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.randomfact.data.db.dao.FactDao
import com.example.randomfact.data.db.model.FactEntity

@Database(entities = [FactEntity::class], version = 1, exportSchema = false)
abstract class FactDatabase : RoomDatabase(){

    abstract fun factDao(): FactDao
}