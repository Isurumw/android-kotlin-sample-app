package com.example.maintenance.Helpers.Models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Property(var id: String, var maintenances: List<Request>): Parcelable