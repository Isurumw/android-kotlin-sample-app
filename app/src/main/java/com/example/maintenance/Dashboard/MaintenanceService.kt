package com.example.maintenance.Dashboard

import com.example.maintenance.Helpers.Models.MainResponse
import io.reactivex.Observable
import com.example.maintenance.Helpers.Models.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface MaintenanceService {

    @POST("graphql")
    fun fetchMaintenanceRequests(@Body body: RequestBody): Observable<MainResponse>

}