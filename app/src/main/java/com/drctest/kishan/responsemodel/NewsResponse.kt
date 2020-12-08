package com.drctest.kishan.responsemodel

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

class Article {
    @SerializedName("source")
    
     val source: List<Sources>? = null

    @SerializedName("author")
    
     val author: String? = null

    @SerializedName("title")
    
     val title: String? = null

    @SerializedName("description")
    
     val description: String? = null

    @SerializedName("url")
    
     val url: String? = null

    @SerializedName("urlToImage")
    
     val urlToImage: String? = null

    @SerializedName("publishedAt")
    
     val publishedAt: String? = null

    @SerializedName("content")
    
     val content: String? = null
}

class Sources {
    @SerializedName("id")
    
     val id: String? = null

    @SerializedName("name")
    
     val name: String? = null

}
