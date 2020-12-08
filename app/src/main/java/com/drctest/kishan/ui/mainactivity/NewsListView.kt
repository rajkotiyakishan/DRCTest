package com.drctest.kishan.ui.mainactivity

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.drctest.kishan.R
import com.drctest.kishan.base.rxjava.autoDispose
import com.drctest.kishan.base.rxjava.throttleClicks
import com.drctest.kishan.responsemodel.Article
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.row_news.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class NewsListView(context: Context) : FrameLayout(context) {
    private val selectedNewsItemClickSubject1: PublishSubject<Article> = PublishSubject.create()
    var selectedNewsListItemClick1: Observable<Article> = selectedNewsItemClickSubject1.hide()
    protected val compositeDisposable = CompositeDisposable()


    var position: Int = -1

    init {
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        View.inflate(context, R.layout.row_news, this)
    }

    fun bind(article: Article, position: Int, isSelected: Boolean) {
        this.position = position
        Glide.with(this).load(article.urlToImage).placeholder(R.drawable.ic_launcher_background).into(ivNews)
        tvTitle.text = article.title
        tvAuther.text = article.author


        tvDate.text = convertDate(article.publishedAt!!.substring(0, article.publishedAt!!.length - 6),"yyyy-MM-dd'T'HH:mm:ss","d MMM yyyy")

        layoutMain.throttleClicks().subscribe {
            selectedNewsItemClickSubject1.onNext(article)

        }.autoDispose(compositeDisposable)
    }
    private fun convertDate(strDate: String,inputFormat: String,outputFormat:String): String{
        val originalFormat: DateFormat = SimpleDateFormat(inputFormat, Locale.ENGLISH)
        val targetFormat: DateFormat = SimpleDateFormat(outputFormat)
        val date: Date = originalFormat.parse(strDate)
        return targetFormat.format(date)
    }


}