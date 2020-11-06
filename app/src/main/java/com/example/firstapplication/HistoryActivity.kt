package com.example.firstapplication

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapplication.history.History
import com.example.firstapplication.history.HistoryAdapter
import com.example.firstapplication.history.HistoryEntry

class HistoryActivity : AppCompatActivity() {

    private lateinit var history: ArrayList<HistoryEntry>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        history = History.getHistory() as ArrayList<HistoryEntry>

        val recyclerView = findViewById<RecyclerView>(R.id.historyRV)
        val adapter = HistoryAdapter(history)

        if(history.isNotEmpty()) {
            val emptyTV = findViewById<TextView>(R.id.emptyTV)
            emptyTV.visibility = View.INVISIBLE
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}