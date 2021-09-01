package com.example.maintenance.Helpers.Models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Request(var id: String, var status: String, var area: Area, var problem: Problem, var details: String, var attachments: List<String>, var createdAt: String, var log: List<Log>): Parcelable