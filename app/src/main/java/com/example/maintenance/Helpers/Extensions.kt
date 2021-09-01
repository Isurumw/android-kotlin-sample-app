package com.example.maintenance.Helpers

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

val DATE_TIME_FORMAT_API = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
val DATE_FORMAT_DISPLAY = "MMM dd, yyyy"
val IMAGE_TYPES = arrayOf("jpeg", "jpg", "gif", "png", "svg")
val VIDEO_TYPES = arrayOf("mp4", "mov", "m4v")
val AUDIO_TYPES = arrayOf("mp3", "3gp", "m4a")

enum class MediaType {
    image, video, audio, default
}

fun String.toFormattedDate(): String? {
    val simpleDateFormat = SimpleDateFormat(DATE_TIME_FORMAT_API, Locale.getDefault())
    val simpleDateDisplayFormat = SimpleDateFormat(DATE_FORMAT_DISPLAY, Locale.getDefault())
    simpleDateFormat.timeZone = TimeZone.getDefault()
    try {
        val date = simpleDateFormat.parse(this)
        return simpleDateDisplayFormat.format(date!!)
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    return null
}

fun String.mediaType(): MediaType {
    if (IMAGE_TYPES.filter { it in this.toLowerCase(Locale.ROOT) }.isNotEmpty()) {
        return MediaType.image
    } else if (VIDEO_TYPES.filter { it in this.toLowerCase(Locale.ROOT) }.isNotEmpty()) {
        return MediaType.video
    } else if (AUDIO_TYPES.filter { it in this.toLowerCase(Locale.ROOT) }.isNotEmpty()) {
        return MediaType.audio
    }
    return MediaType.default
}

