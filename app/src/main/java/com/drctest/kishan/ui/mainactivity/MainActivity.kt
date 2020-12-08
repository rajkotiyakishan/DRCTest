package com.drctest.kishan.ui.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.drctest.kishan.R
import com.drctest.kishan.base.BaseActivity

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

        mainViewModel.newsListResponse.observe(this, Observer { holidayResponse ->

            hideProgress()



        })

        mainViewModel.errorMessage.observe(this,{errorMessage->
            hideProgress()
            Toast.makeText(this,errorMessage,Toast.LENGTH_SHORT).show()
        })

    }
}