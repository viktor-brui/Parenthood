package org.unicef.parenthood.repository

import org.unicef.parenthood.repository.model.ArticleEntity
import org.unicef.parenthood.repository.model.TestEntity
import org.unicef.parenthood.repository.model.TestQuestion

class FakeRepository() {
    private val testEntity = TestEntity("oXSzsjXzzJbM0Nehz6rr",
        "oXSzsjXzzJbM0Nehz6OA",
        arrayListOf(
            TestQuestion(0, "Question", "Answer"),
            TestQuestion(0, "Question1", "Answer1"),
            TestQuestion(0, "Question2", "Answer2")),
        authorName = "TestAuthor")

    private val entry0 = ArticleEntity(
        id = "oXSzsjXzzJbM0Nehz6OA",
        author = "Author",
        title = "Title",
        test = TestEntity("oXSzsjXzzJbM0Nehz6rr",
            "oXSzsjXzzJbM0Nehz6OA",
            arrayListOf(
                TestQuestion(0, "Question", "Answer"),
                TestQuestion(0, "Question1", "Answer1"),
                TestQuestion(0, "Question2", "Answer2")),
            authorName = "TestAuthor"
        ),
        content = "Most of the advice I share is focused on infants, toddlers and preschoolers, but since my own children are now well past those years (my oldest just turned 21!), it occurred to me that I should be sharing more often from my “long view” perspective. Like most parents, I’ve had my worries. For instance, despite"
        , categories = listOf("Safety", "Food"), description = "description"
    )


    private val entry1 = ArticleEntity(
        id = "oXSzsjXzzJbM0Nehz6OB",
        author = "Author",
        title = "Title",
        test = TestEntity("oXSzsjXzzJbM0Nehz6rr",
            "oXSzsjXzzJbM0Nehz6OB",
            arrayListOf(
                TestQuestion(0, "Question", "Answer"),
                TestQuestion(0, "Question1", "Answer1"),
                TestQuestion(0, "Question2", "Answer2")),
            authorName = "TestAuthor"
        ),
        content = "Most of the advice I share is focused on infants, toddlers and preschoolers, but since my own children are now well past those years (my oldest just turned 21!), it occurred to me that I should be sharing more often from my “long view” perspective. Like most parents, I’ve had my worries. For instance, despite"
        , categories = listOf("Safety", "Food"), description = "description"
    )

    private val entry2 = ArticleEntity(
        id = "oXSzsjXzzJbM0Nehz6OC",
        author = "Author",
        title = "Title",
        test = TestEntity("oXSzsjXzzJbM0Nehz6rr",
            "oXSzsjXzzJbM0Nehz6OC",
            arrayListOf(
                TestQuestion(0, "Question", "Answer"),
                TestQuestion(0, "Question1", "Answer1"),
                TestQuestion(0, "Question2", "Answer2")),
            authorName = "TestAuthor"
        ),
        content = "Most of the advice I share is focused on infants, toddlers and preschoolers, but since my own children are now well past those years (my oldest just turned 21!), it occurred to me that I should be sharing more often from my “long view” perspective. Like most parents, I’ve had my worries. For instance, despite"
        , categories = listOf("Safety", "Food"), description = "description"
    )


    fun getRecommended(): List<ArticleEntity> {
        return listOf(entry0, entry1, entry2)
    }

    suspend fun getDiscoverable(): List<ArticleEntity> {
        return listOf(entry0, entry1, entry2)
    }

    suspend fun addTest(){
    }

    suspend fun addTestFailed(): String{
        return "-1"
    }

    suspend fun getTest(articleId: String): TestEntity? {
        return testEntity
    }

    suspend fun getTestFailed(): TestEntity? {
        return null
    }

    suspend fun addArticle(articleEntity: ArticleEntity){
    }
}