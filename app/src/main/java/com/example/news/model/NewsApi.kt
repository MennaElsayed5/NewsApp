package com.example.news.model


data class NewsApi(
    val articles: List<Article>
)

data class Article(
    val sourceName: String?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
)


