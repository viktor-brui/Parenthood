package org.unicef.parenthood.repository.model

data class TestEntity(
    val id: String,
    val articleId: String,
    val questions: ArrayList<TestQuestion>,
    val authorName: String
) {
}