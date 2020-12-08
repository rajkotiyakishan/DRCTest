package com.drctest.kishan.responsemodel

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


class NewsResponse {

    @SerializedName("status")
    val status: String? = null

    @SerializedName("message")
    val message: String? = null

    @SerializedName("code")
    val code: String? = null

    @SerializedName("totalResults")
    val totalResults: Int? = null

    @SerializedName("articles")
    val articles: List<Article>? = null


}

class Article() : Parcelable {
    @SerializedName("source")
    
     var source: Sources? = null

    @SerializedName("author")

    var author: String? = null

    @SerializedName("title")

    var title: String? = null

    @SerializedName("description")

    var description: String? = null

    @SerializedName("url")

    var url: String? = null

    @SerializedName("urlToImage")

    var urlToImage: String? = null

    @SerializedName("publishedAt")

    var publishedAt: String? = null

    @SerializedName("content")

    var content: String? = null

    constructor(parcel: Parcel) : this() {
        source = parcel.readParcelable(Sources::class.java.classLoader)
        author = parcel.readString()
        title = parcel.readString()
        description = parcel.readString()
        url = parcel.readString()
        urlToImage = parcel.readString()
        publishedAt = parcel.readString()
        content = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(source, flags)
        parcel.writeString(author)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(url)
        parcel.writeString(urlToImage)
        parcel.writeString(publishedAt)
        parcel.writeString(content)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Article> {
        override fun createFromParcel(parcel: Parcel): Article {
            return Article(parcel)
        }

        override fun newArray(size: Int): Array<Article?> {
            return arrayOfNulls(size)
        }
    }
}

class Sources() : Parcelable {
    @SerializedName("id")
    var id: String? = null

    @SerializedName("name")
    var name: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        name = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Sources> {
        override fun createFromParcel(parcel: Parcel): Sources {
            return Sources(parcel)
        }

        override fun newArray(size: Int): Array<Sources?> {
            return arrayOfNulls(size)
        }
    }

}
