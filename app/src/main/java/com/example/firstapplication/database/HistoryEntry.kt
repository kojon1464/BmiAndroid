package com.example.firstapplication.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.firstapplication.bmi.Unit

@Entity(tableName = "history_entries")
data class HistoryEntry(
    @PrimaryKey(autoGenerate = true) val entryId: Int,
    @ColumnInfo(name="mass") val mass: Int,
    @ColumnInfo(name="height") val height: Int,
    @ColumnInfo(name="bmi") val bmi: Double,
    @ColumnInfo(name="date") val date: Long,
    @ColumnInfo(name="unit") val unit: Unit
)