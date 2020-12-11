package com.example.firstapplication.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDao {
    @Insert
    suspend fun insert(historyEntry: HistoryEntry)

    @Query("SELECT * FROM history_entries ORDER BY date LIMIT :number")
    fun getLast(number: Int): LiveData<List<HistoryEntry>>
}