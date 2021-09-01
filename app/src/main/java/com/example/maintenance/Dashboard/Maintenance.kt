package com.example.maintenance.Dashboard

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.maintenance.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Maintenance : AppCompatActivity() {
    private val TAG = "Maintenance"
    private val  viewModal: MaintenanceViewModal by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maintenance)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModal.fetchRequests()
    }
}
