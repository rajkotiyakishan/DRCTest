package com.drctest.kishan.base

import android.annotation.SuppressLint
import android.content.Context
import androidx.multidex.MultiDexApplication

class MyApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }


}