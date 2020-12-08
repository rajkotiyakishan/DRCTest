package com.drctest.kishan.ui.mainactivity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.drctest.kishan.base.BaseViewModel
import com.drctest.kishan.base.rxjava.autoDispose
import com.drctest.kishan.responsemodel.NewsResponse
import com.drctest.kishan.service.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val apiService: ApiService) : BaseViewModel() {

    val newsListResponse: MutableLiveData<NewsResponse> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()


    fun getNewsList(apiKey: String) {
        apiService.getNews(apiKey)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ newsResponse ->
                if (newsResponse.status == "ok") {
                    newsListResponse.value = newsResponse
                } else {
                    errorMessage.value = newsResponse.message

                }
            }, {
                Log.e("Error",it.localizedMessage)

            }).autoDispose(compositeDisposable)

    }

}