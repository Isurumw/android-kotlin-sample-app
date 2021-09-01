package com.example.maintenance.Dashboard.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.maintenance.Helpers.MediaType
import com.example.maintenance.Helpers.Models.Asset
import com.example.maintenance.Helpers.Models.Request
import com.example.maintenance.Helpers.mediaType
import com.example.maintenance.R
import com.squareup.picasso.Picasso

class MediaViewHolder(view: View): RecyclerView.ViewHolder(view) {
    var media: ImageView = view.findViewById(R.id.imgMedia)
}

class MediaRecyclerViewAdapter(private var medias: List<String>): RecyclerView.Adapter<MediaViewHolder>() {

    fun loadNewData(newMedias: List<String>) {
        medias = newMedias
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.media, parent, false)
        return  MediaViewHolder(v)
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        val media = medias[position]

        if (media.mediaType() == MediaType.image) {
            Picasso.get().load(media).error(R.drawable.placeholder).placeholder(R.drawable.placeholder).into(holder.media)
        } else if (media.mediaType() == MediaType.video) {
            holder.media.setBackgroundResource(R.drawable.placeholder_video)
        } else if (media.mediaType() == MediaType.audio) {
            holder.media.setBackgroundResource(R.drawable.placeholder_audio)
        } else {
            holder.media.setBackgroundResource(R.drawable.placeholder)
        }
    }

    override fun getItemCount(): Int {
        return medias.size
    }
}