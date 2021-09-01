package com.example.maintenance.Dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.maintenance.Helpers.Models.Request
import com.example.maintenance.Helpers.toFormattedDate
import com.example.maintenance.R
import com.squareup.picasso.Picasso

class RequestViewHolder(view: View): RecyclerView.ViewHolder(view) {
    var icon: ImageView = view.findViewById(R.id.imgIcon)
    var request: TextView = view.findViewById(R.id.lblRequest)
    var time: TextView = view.findViewById(R.id.lblTime)
}

class MaintenanceRecyclerViewAdapter(private var requests: List<Request>): RecyclerView.Adapter<RequestViewHolder>() {

    fun loadNewData(newRequests: List<Request>) {
        requests = newRequests
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.browse, parent, false)
        return RequestViewHolder(view)
    }

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
        val request = requests[position]

        Picasso
            .get()
            .load(request.problem.assets[0].value)
            .error(R.drawable.placeholder)
            .placeholder(R.drawable.placeholder)
            .into(holder.icon)
        holder.request.text = "${request.area.name} - ${request.problem.name}"
        holder.time.text = "Reported on ${request.createdAt.toFormattedDate()}"
    }

    override fun getItemCount(): Int {
        return if (requests.isNotEmpty()) requests.size else 0
    }
}
