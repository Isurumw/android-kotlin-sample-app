package com.example.maintenance.Dashboard.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.maintenance.Helpers.Models.Log
import com.example.maintenance.Helpers.toFormattedDate
import com.example.maintenance.R
import com.squareup.picasso.Picasso

class TimelineViewHolder(view: View): RecyclerView.ViewHolder(view) {
    var status: TextView = view.findViewById(R.id.txtStatus)
    var date: TextView = view.findViewById(R.id.txtLogDate)
    var icon: ImageView = view.findViewById(R.id.imgLog)
    var line: TextView = view.findViewById(R.id.vertical_line)
}

class TimelineRecyclerViewAdapter(private var logs: List<Log>): RecyclerView.Adapter<TimelineViewHolder>() {

    fun loadNewData(newLogs: List<Log>) {
        logs = newLogs
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelineViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.timeline_item, parent, false)
        return TimelineViewHolder(v)
    }

    override fun onBindViewHolder(holder: TimelineViewHolder, position: Int) {
        val log = logs[position]

        holder.icon.setBackgroundResource(if(position == 0) R.drawable.reported else R.drawable.user)
        holder.status.text = log.status
        holder.date.text = log.date.toFormattedDate()
        holder.line.isVisible = position < logs.size - 1
    }

    override fun getItemCount(): Int {
        return logs.size
    }
}