package com.example.maintenance.Helpers.Models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class MainResponse(var data: DataResponse): Parcelable

@Parcelize
class DataResponse(var properties: List<Property>): Parcelable
