package com.example.maintenance.Dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.maintenance.Helpers.Models.MainResponse
import com.example.maintenance.Helpers.Models.Request
import com.example.maintenance.Helpers.Models.RequestBody
import com.example.maintenance.Helpers.Models.Variables
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

val EMPTY_REQUEST_LIST: List<Request> = Collections.emptyList()

@HiltViewModel
class MaintenanceViewModal @Inject constructor(
    private var api: MaintenanceService
): ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val mutableRequests = MutableLiveData<List<Request>>()
    val requests: LiveData<List<Request>> get() = mutableRequests

    private val mutableSelectedRequests = MutableLiveData<Request>()
    val selectedRequests: LiveData<Request> get() = mutableSelectedRequests

    fun fetchRequests() {
        val variables = Variables("758")
        val body = RequestBody(
            "query getMaintenancesList(\$id: [ID]){ properties(id: \$id){ maintenances { id status area { code name } problem { code name assets { value } } details attachments createdAt log { status date } } } }",
            variables
        )

        val call = api.fetchMaintenanceRequests(body)
        compositeDisposable.add(
            call.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::onResponse, this::onError)
        )
    }

    fun setSelectedRequest(position: Int) {
        mutableSelectedRequests.value = mutableRequests.value!![position]
    }

    private fun onResponse(response: MainResponse) {
        val requests = (if (response.data.properties.count() > 0)  response.data.properties[0].maintenances else EMPTY_REQUEST_LIST).reversed()
        mutableRequests.value = requests
    }

    private fun onError(e: Throwable) {
        mutableRequests.value = EMPTY_REQUEST_LIST
    }


}