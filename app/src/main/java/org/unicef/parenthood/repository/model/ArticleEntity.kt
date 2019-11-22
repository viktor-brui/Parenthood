package org.unicef.parenthood.repository.model

import androidx.room.Entity
//todo
@Entity(tableName = "articles")
data class ArticleEntity(
    val id: String,
    val title: String,
    val tests: ArrayList<TestEntity>,
    var reads: String,
    val content: String, //что-то библиотечное из парсера рсс
    var progress: Int //для прогрессбара
)