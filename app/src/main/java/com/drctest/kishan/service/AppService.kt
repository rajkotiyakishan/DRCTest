package com.drctest.kishan.service

import android.content.Context
import com.drctest.kishan.BuildConfig
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.drctest.kishan.base.network.setupNetworkSecurity
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit

object AppService {

    fun createService(context: Context): ApiService {
        return setupRetrofit(context).create(ApiService::class.java)
    }

    fun setupOkHttp(context: Context): OkHttpClient {
        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        val cacheDir = File(context.cacheDir, "HttpCache")
        val cache = Cache(cacheDir, cacheSize.toLong())
        val builder = OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .setupNetworkSecurity(context)
            .cache(cache)
            //.addInterceptor(InterceptorHeaders())
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
        }
        return builder.build()
    }

    private fun setupRetrofit(context: Context): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL )
            .client(setupOkHttp(context))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setLenient().registerTypeAdapter(
                        Date::class.java,
                        JsonDeserializer { json, _, _ -> Date(json.asJsonPrimitive.asLong) })
                        .create()
                )
            )
            .build()
    }
}