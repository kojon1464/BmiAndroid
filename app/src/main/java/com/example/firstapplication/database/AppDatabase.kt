package com.example.firstapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.firstapplication.bmi.UnitConverter

@Database(entities = [HistoryEntry::class], version = 1)
@TypeConverters(UnitConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao

    companion object{
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "bmidb"
                ).build()
            }

            return instance as AppDatabase
        }
    }
}