package com.drctest.kishan.ui.mainactivity

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drctest.kishan.responsemodel.Article
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class NewsListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val adapterItems: MutableList<AdapterItem> = mutableListOf()
    var isSelectedPosition: Int = -1

    private val selectedNewsItemClickSubject: PublishSubject<Article> = PublishSubject.create()
    var selectedNewListItemClick: Observable<Article> = selectedNewsItemClickSubject.hide()

    private val selectedLinkItemClickSubject: PublishSubject<Article> = PublishSubject.create()
    var selectedNewLinkItemClick: Observable<Article> = selectedLinkItemClickSubject.hide()
    protected val compositeDisposable = CompositeDisposable()


    companion object {
        private const val TYPE_1 = 0
    }

    var newsList: List<Article> = mutableListOf()
        set(value) {
            field = value
            updateAdapterItem()
        }


    private fun updateAdapterItem() {
        val adapterItems = mutableListOf<AdapterItem>()
        for (i in 0 until newsList.size) {
            adapterItems.add(AdapterItem.Item(newsList[i]))
        }
        this.adapterItems.addAll(adapterItems)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HolidayViewHolder(NewsListView(viewGroup.context).apply {
            selectedNewsListItemClick1.subscribe {
                selectedNewsItemClickSubject.onNext(it)
            }
            selectedNewsLinkItemClick1.subscribe {
                selectedLinkItemClickSubject.onNext(it)
            }
        })
    }

    override fun getItemCount(): Int {
        return adapterItems.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val adapterItem = adapterItems[position]
        when (adapterItem) {
            is AdapterItem.Item ->
                (viewHolder.itemView as NewsListView).bind(
                    adapterItem.newsItem,
                    position,
                    position == isSelectedPosition
                )
        }

    }

    private class HolidayViewHolder(view: View) : RecyclerView.ViewHolder(view)

    sealed class AdapterItem(val type: Int) {
        data class Item(val newsItem: Article) : AdapterItem(TYPE_1)

    }
}