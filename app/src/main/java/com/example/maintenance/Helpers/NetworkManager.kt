package com.example.maintenance.Helpers

import com.example.maintenance.BuildConfig
import com.example.maintenance.R
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class NetworkManager @Inject constructor() {
    val okHttpClient = OkHttpClient.Builder().apply {
        addInterceptor (
            Interceptor { chain ->
                val builder = chain.request().newBuilder()
                builder.header("x-access-token", "...token....")
                return@Interceptor chain.proceed(builder.build())
            }
        )
    }.build()
    val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    fun <T> service(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }
}
