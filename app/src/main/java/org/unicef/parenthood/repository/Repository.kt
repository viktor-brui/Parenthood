package org.unicef.parenthood.repository

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.prof.rssparser.Article
import com.prof.rssparser.Parser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import org.unicef.parenthood.repository.model.ArticleEntity
import org.unicef.parenthood.repository.model.TestEntity

/**
 * hub for network requests
 */
class Repository {

    private val pageCount = 10 // maximum number of articles per feed
    private val articlesCollection = "articles"
    private val testsCollection = "tests"

    private val urls = listOf("https://www.psychologytoday.com/intl/blog/singletons/feed")

    private val firestore = Firebase.firestore

    suspend fun getRecommended(): List<ArticleEntity> {
        val articlesSnapshot = firestore.collection(articlesCollection).get().await()
        return articlesSnapshot.map { article ->
            article.toObject<ArticleEntity>().apply {
                id = article.id
            }
        }
    }

    suspend fun addArticle(articleEntity: ArticleEntity) {
        val newArticle = mapOf(
            "author" to articleEntity.author,
            "categories" to articleEntity.categories,
            "content" to articleEntity.content,
            "description" to articleEntity.description,
            "link" to articleEntity.link,
            "mainImage" to articleEntity.mainImage,
            "title" to articleEntity.title
        )

        firestore
            .collection(articlesCollection)
            .document()
            .set(newArticle)
            .await()
    }

    suspend fun addTest(testEntity: TestEntity, articleId: String) {
        val newTest = mapOf(
            "questions" to testEntity.questions,
            "authorName" to testEntity.authorName
        )
        firestore
            .collection(testsCollection)
            .document()
            .set(newTest)
            .await()

        val addedTest = firestore
            .collection(testsCollection)
            .get()
            .await()
            .last()

        val updatedArticle = firestore
            .collection(articlesCollection)
            .get()
            .await()
            .find { article -> article.id == articleId }
            ?.data
        if (updatedArticle != null) {
            updatedArticle["testId"] = addedTest.id
            firestore
                .collection(articlesCollection)
                .document()
                .set(updatedArticle)
                .await()
        }
    }

    suspend fun getTest(testId: String): TestEntity? {
        return firestore
            .collection(testsCollection)
            .get()
            .await()
            .find { test -> test.id == testId }
            ?.toObject()
    }

    suspend fun getDiscoverable(): List<ArticleEntity> {
        return urls.flatMap {
            fetchFeed(it)
        }
    }

    private suspend fun fetchFeed(url: String): List<ArticleEntity> {
        return withContext(Dispatchers.IO) {
            val parser = Parser()
            val list: List<Article> = parser.getArticles(url)
            list.take(pageCount).map { article ->
                ArticleEntity(
                    id = article.guid,
                    author = article.author ?: "",
                    title = article.title ?: "",
                    testId = "",
                    content = article.content ?: "",
                    categories = article.categories,
                    description = article.description ?: "",
                    link = article.link ?: "",
                    mainImage = article.image ?: ""
                )
            }
        }
    }
}