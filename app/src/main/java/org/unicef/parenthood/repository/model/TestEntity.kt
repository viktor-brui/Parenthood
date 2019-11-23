package org.unicef.parenthood.repository.model

data class TestEntity(
    val id: String = "",
    val articleId: String = "",
    val questions: List<String> = emptyList(),
    val authorName: String = ""
) {
}