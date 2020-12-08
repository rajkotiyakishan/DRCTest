package com.drctest.kishan.ui.newsdetailactivity

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.drctest.kishan.R
import com.drctest.kishan.base.BaseActivity
import com.drctest.kishan.base.rxjava.autoDispose
import com.drctest.kishan.base.rxjava.throttleClicks
import com.drctest.kishan.responsemodel.Article
import kotlinx.android.synthetic.main.activity_news_detail.*
import kotlinx.android.synthetic.main.fullscreen_image.*
import kotlinx.android.synthetic.main.toolbar_main.*

class NewsDetailActivity : BaseActivity() {
    private lateinit var article: Article
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
        settoolbarIcon()
        intent.extras?.let {
            article = intent.getParcelableExtra<Article>("article") as Article
            setData()
        }

    }

    private fun setData() {
        tvTitle.text = article.title
        tvDesc.text = article.description
        tvContent.text = article.content
        Glide.with(this).load(article.urlToImage).placeholder(R.drawable.ic_launcher_background).into(ivDetailsNews)
        ivDetailsNews.throttleClicks().subscribe {
            showFullImage()
        }.autoDispose(compositeDisposable)

    }

    private fun showFullImage() {
       val fullImageDialog = Dialog(this)
        val lWindowParams = WindowManager.LayoutParams()
        fullImageDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        fullImageDialog.setCancelable(true)
        progressBar.window!!.setBackgroundDrawableResource(R.color.white)
        lWindowParams.width = WindowManager.LayoutParams.MATCH_PARENT
        lWindowParams.height = WindowManager.LayoutParams.MATCH_PARENT
        fullImageDialog.setContentView(R.layout.fullscreen_image)
        Glide.with(this).load(article.urlToImage).placeholder(R.drawable.ic_launcher_background).into(fullImageDialog.ivFullImage)

        fullImageDialog.window!!.attributes = lWindowParams
        fullImageDialog.window!!.setGravity(Gravity.CENTER)
        fullImageDialog.show()
    }

    private fun settoolbarIcon() {
        ivHamburger.setImageResource(R.drawable.ic_back)
        ivNotification.setImageResource(R.drawable.ic_share)
        ivHamburger.throttleClicks().subscribe {
            finish()
        }.autoDispose(compositeDisposable)

    }
}