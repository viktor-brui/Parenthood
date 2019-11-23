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
class Repository() {
    private val MAX_RANGE = 10 // maximum number of articles per feed
    private val ARTICLES_COLLECTION = "articles"
    private val TESTS_COLLECTION = "tests"

    private val urls = listOf("https://www.psychologytoday.com/intl/blog/singletons/feed")

    private val firestore = Firebase.firestore

    suspend fun getRecommended(): List<ArticleEntity> {
        val articlesSnapshot = firestore.collection(ARTICLES_COLLECTION).get().await()
        return articlesSnapshot.map { article ->
            article.toObject<ArticleEntity>().apply {
                id = article.id
            }
        }
    }

    suspend fun addArticle(articleEntity: ArticleEntity) {

        val newArticle = hashMapOf(
            "author" to articleEntity.author,
            "categories" to articleEntity.categories,
            "content" to articleEntity.content,
            "description" to articleEntity.description,
            "link" to articleEntity.link,
            "mainImage" to articleEntity.mainImage,
            "title" to articleEntity.title
        )

        val snapshot = firestore.collection(TESTS_COLLECTION).document()
        snapshot
            .set(newArticle)
            .addOnSuccessListener {
                //                todo
            }
            .addOnFailureListener {

                //                   todo
            }
    }

    suspend fun addTest(testEntity: TestEntity) {

        val newTest = hashMapOf(
            "articleId" to testEntity.articleId,
            "questions" to testEntity.questions,
            "authorName" to testEntity.authorName
        )

        firestore.collection(TESTS_COLLECTION).document() //???
            .set(newTest)
            .addOnSuccessListener {
                //                todo
            }
            .addOnFailureListener {

                //                   todo
            }
    }

    suspend fun getTest(articleId: String): TestEntity? {
        val query = firestore.collection(TESTS_COLLECTION).whereEqualTo("articleId", "CA")
        val result =
            query.get()
                .addOnSuccessListener { documents ->
                    documents.first()
                }
                .addOnFailureListener { exception ->
                    //                Log.w(TAG, "Error getting documents: ", exception)
                }.result
        return result?.documents?.first()?.toObject(TestEntity::class.java)
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
            list.take(MAX_RANGE).map { article ->
                ArticleEntity(
                    id = article.guid,
                    author = article.author ?: "",
                    title = article.title ?: "",
                    test = null,
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