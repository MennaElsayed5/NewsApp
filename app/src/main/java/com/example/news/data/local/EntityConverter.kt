package com.example.news.data.local

import com.example.news.model.Article
import com.example.news.model.CachedArticle
import com.example.news.model.CachedData
import com.example.news.model.NewsApi

fun fromNewsToEntity(newsApi: NewsApi): CachedData {
    var articleApi=newsApi.articles
    var articleList:ArrayList<CachedArticle> = arrayListOf()

    for (article: Article in articleApi)
    {
        articleToEntity(article)?.let { articleList.add(it) }
    }
    return CachedData(article = articleList)
}

fun articleToEntity(article: Article): CachedArticle? {
    var cashedArticle: CachedArticle?=null
    cashedArticle?.sourceName=article.source.name
    cashedArticle?.author=article.author
    cashedArticle?.content=article.content
    cashedArticle?.description=article.description
    cashedArticle?.publishedAt=article.publishedAt
    cashedArticle?.title=article.title
    cashedArticle?.url=article.url
    cashedArticle?.urlToImage=article.urlToImage
    return cashedArticle?.let { it }
}