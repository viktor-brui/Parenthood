package org.unicef.parenthood.repository.model

data class ArticleEntity(
    var id: String? = "",
    val author: String? = "",
    val title: String? = "",
    val test: TestEntity?,
    val content: String? = "",
    val categories: List<String>,
    val description: String? = "",
    val link:String? = "",
    val mainImage: String? = "",
    val publicationDate: String? = ""
)