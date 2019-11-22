package org.unicef.parenthood.repository.model

import androidx.room.Entity

@Entity(tableName = "tests")
data class TestEntity(
    val id: String,
    val questions: ArrayList<TestQuestion>,
    val authorName: String
) {
}