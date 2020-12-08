package com.drctest.kishan.ui.mainactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.drctest.kishan.R
import com.drctest.kishan.base.BaseActivity
import com.drctest.kishan.base.rxjava.autoDispose
import com.drctest.kishan.ui.newsdetailactivity.NewsDetailActivity
import com.drctest.kishan.ui.webviewactivity.WebViewActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = MainViewModel(appService)
        observeViewModel()
        getNewsList()


    }

    private fun getNewsList() {
        showProgress()
        mainViewModel.getNewsList("9a0c8e375ada4198a26f7a52638c4b78")
    }

    private fun observeViewModel() {

        mainViewModel.newsListResponse.observe(this, Observer { NewsResponse ->
            hideProgress()
            val newsListAdapter = NewsListAdapter()
            rvNews.layoutManager = LinearLayoutManager(this)
            rvNews.adapter = newsListAdapter
            NewsResponse.articles?.let {
                newsListAdapter.newsList = it
            }
            newsListAdapter.selectedNewListItemClick.subscribe { news ->
                val intent = Intent(this,NewsDetailActivity::class.java)
                intent.putExtra("article",news)
                startActivity(intent)
             }.autoDispose(compositeDisposable)

            newsListAdapter.selectedNewLinkItemClick.subscribe { news ->
                val intent = Intent(this,WebViewActivity::class.java)
                intent.putExtra("url",news.url)
                startActivity(intent)
             }.autoDispose(compositeDisposable)


        })

        mainViewModel.errorMessage.observe(this,{errorMessage->
            hideProgress()

            Toast.makeText(this,errorMessage,Toast.LENGTH_SHORT).show()
        })

    }
}