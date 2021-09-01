package com.example.maintenance.di

import android.content.Context
import com.example.maintenance.Dashboard.MaintenanceService
import com.example.maintenance.Helpers.NetworkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideMaintenanceService(networkManager: NetworkManager) : MaintenanceService {
        return networkManager.service(MaintenanceService::class.java)
    }

}