package org.unicef.parenthood.repository.model

data class TestEntity(
    val id: String,
    val questions: ArrayList<TestQuestion>,
    val authorName: String
) {
}