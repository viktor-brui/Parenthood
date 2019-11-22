package org.unicef.parenthood.repository.model

import androidx.room.Entity
//todo
data class ArticleEntity(
    val id: String,
    val title: String,
    val test: TestEntity,
    val content: String,
    val categories: String
)