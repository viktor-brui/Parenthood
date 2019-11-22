package org.unicef.parenthood.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RestApiFactory {
    val BASE_URL = ""
    //todo

    fun buildArticleService(): ArticleService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(ArticleService::class.java)
    }
}