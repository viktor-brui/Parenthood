package org.unicef.parenthood.repository.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TestEntity(
    val questions: List<String> = emptyList(),
    val authorName: String = ""
): Parcelable
