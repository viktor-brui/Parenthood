package org.unicef.parenthood.repository.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.PropertyName
import com.google.firebase.firestore.ServerTimestamp

data class ArticleEntity(
    var id: String? = "",
    val author: String? = "",
    val title: String? = "",
    val test: TestEntity? = TestEntity(),
    val content: String? = "",
    val categories: List<String> = emptyList(),
    val description: String? = "",
    val link:String? = "",
    @PropertyName("mainimage")
    val mainImage: String? = ""
)