package com.example.maintenance.Helpers.Models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Log(var status: String, var date: String): Parcelable