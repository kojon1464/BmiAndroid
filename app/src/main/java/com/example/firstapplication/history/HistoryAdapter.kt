package com.example.firstapplication.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapplication.App
import com.example.firstapplication.R
import java.text.DateFormat
import java.util.*


class HistoryAdapter(private val history: List<HistoryEntry>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val massTV: TextView = itemView.findViewById(R.id.massTV)
        val heightTV: TextView = itemView.findViewById(R.id.heightTV)
        val bmiTV: TextView = itemView.findViewById(R.id.bmiTV)
        val dateTV: TextView = itemView.findViewById(R.id.dateTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_history, parent, false)
        return ViewHolder(contactView)
    }

    override fun getItemCount(): Int {
        return history.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = App.getContext()
        val entry = history[position]
        holder.apply {
            massTV.text = context.getString(R.string.history_mass, entry.mass, entry.unit.mass_unit)
            heightTV.text = context.getString(R.string.history_height, entry.height, entry.unit.height_unit)
            bmiTV.text = context.getString(R.string.history_bmi, entry.bmi)
            dateTV.text = convertLongToTime(entry.date)
        }
    }

    private fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = DateFormat.getDateTimeInstance()
        return format.format(date)
    }
}