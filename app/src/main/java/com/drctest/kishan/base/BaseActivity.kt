package com.drctest.kishan.base

import android.app.Activity
import android.app.Dialog
import android.app.UiModeManager
import android.content.res.Configuration
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.drctest.kishan.R
import com.drctest.kishan.service.ApiService
import com.drctest.kishan.base.network.NetworkObserver
import com.drctest.kishan.service.AppService
import io.reactivex.disposables.CompositeDisposable

open class BaseActivity : AppCompatActivity() {

    lateinit var networkObserver: NetworkObserver
    lateinit var appService: ApiService
    lateinit var progressBar : Dialog
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
        appService = AppService.createService(this)
        initializeLoader()
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    private fun initializeLoader() {
        progressBar = Dialog(this)
        val lWindowParams = WindowManager.LayoutParams()
        progressBar.requestWindowFeature(Window.FEATURE_NO_TITLE)
        progressBar.setCancelable(false)
        progressBar.window!!.setBackgroundDrawableResource(R.color.text_black_transparent)
        lWindowParams.width = WindowManager.LayoutParams.MATCH_PARENT
        progressBar.setContentView(R.layout.loader_view)
        progressBar.window!!.attributes = lWindowParams
        progressBar.window!!.setGravity(Gravity.CENTER)
    }

    fun showProgress(){
        if (!progressBar.isShowing){
            progressBar.show()
        }
    }
    fun hideProgress(){
        if (progressBar.isShowing){
            progressBar.hide()
        }
    }

}