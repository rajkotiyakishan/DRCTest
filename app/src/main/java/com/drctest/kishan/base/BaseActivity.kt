package com.drctest.kishan.base

import android.app.Activity
import android.app.UiModeManager
import android.content.res.Configuration
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.drctest.kishan.service.ApiService
import com.drctest.kishan.base.network.NetworkObserver
import io.reactivex.disposables.CompositeDisposable

open class BaseActivity : AppCompatActivity() {

    lateinit var networkObserver: NetworkObserver
    lateinit var appService: ApiService
    lateinit var baseUserId: String
    private fun isAndroidTV(): Boolean {
        val uiModeManager = getSystemService(Activity.UI_MODE_SERVICE) as UiModeManager
        return uiModeManager.currentModeType == Configuration.UI_MODE_TYPE_TELEVISION
    }

    protected val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        if (isAndroidTV()) {
            requestWindowFeature(Window.FEATURE_OPTIONS_PANEL)
        }
        super.onCreate(savedInstanceState)
        networkObserver = NetworkObserver(application)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

}