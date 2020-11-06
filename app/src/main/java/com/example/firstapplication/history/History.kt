package com.example.firstapplication.history

import android.content.Context
import com.example.firstapplication.App
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

object History {

    private const val PREFERENCES = "history"
    private const val KEY = "history"

    fun getHistory(): MutableList<HistoryEntry> {
        val sharedPreferences = App.getContext().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)

        val listString = sharedPreferences.getString(KEY, null)
        val gson = Gson()
        val history: MutableList<HistoryEntry>

        history = if(listString == null) {
            ArrayList()
        } else {
            val type = object : TypeToken<MutableList<HistoryEntry>>() {}.type
            gson.fromJson<MutableList<HistoryEntry>>(listString, type)
        }

        return history
    }

    fun saveHistory(history: MutableList<HistoryEntry>) {
        val gson = Gson()
        val sharedPreferences = App.getContext().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("history", gson.toJson(history))
        editor.apply()
    }
}