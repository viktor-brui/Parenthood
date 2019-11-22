package org.unicef.parenthood.repository.model

data class ArticleEntity(
    var id: String,
    val title: String,
    val test: TestEntity?,
    val content: String,
    val categories: MutableList<String>
)