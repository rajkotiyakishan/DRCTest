package com.drctest.kishan.ui.webviewactivity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.drctest.kishan.R
import com.drctest.kishan.base.BaseActivity
import com.drctest.kishan.base.rxjava.autoDispose
import com.drctest.kishan.base.rxjava.throttleClicks
import kotlinx.android.synthetic.main.activity_web_view.*
import kotlinx.android.synthetic.main.toolbar_main.*

class WebViewActivity : BaseActivity() {
    private var webUrl = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        settoolbarIcon()
        webUrl = intent.getStringExtra("url")!!
        webView.settings.javaScriptEnabled = true

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        webView.loadUrl(webUrl)
    }


    private fun settoolbarIcon() {
        ivHamburger.setImageResource(R.drawable.ic_back)
        ivNotification.visibility = View.GONE
        ivHamburger.throttleClicks().subscribe {
            finish()
        }.autoDispose(compositeDisposable)

    }


}