package com.example.maintenance.Helpers.Models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Problem(var code: String, var name: String, var assets: List<Asset>): Parcelable