package com.example.firstapplication

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapplication.database.AppDatabase
import com.example.firstapplication.history.HistoryAdapter


class HistoryActivity : SensorActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        AppDatabase.getInstance(applicationContext).historyDao().getLast(10).observe(this, Observer{ history ->
            val recyclerView = findViewById<RecyclerView>(R.id.historyRV)
            val adapter = HistoryAdapter(history)

            if(history.isNotEmpty()) {
                val emptyTV = findViewById<TextView>(R.id.emptyTV)
                emptyTV.visibility = View.INVISIBLE
            }

            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this)
        })
    }
}