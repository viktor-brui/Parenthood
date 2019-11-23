package org.unicef.parenthood.repository.model

import android.os.Parcelable
import com.google.firebase.firestore.PropertyName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArticleEntity(
    var id: String? = "",
    val author: String? = "",
    val title: String? = "",
    val testId: String? = "",
    val content: String? = "",
    val categories: List<String> = emptyList(),
    val description: String? = "",
    val link:String? = "",
    @PropertyName("mainimage")
    val mainImage: String? = ""
): Parcelable