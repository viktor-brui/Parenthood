package org.unicef.parenthood.repository

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.prof.rssparser.Article
import com.prof.rssparser.Parser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.unicef.parenthood.repository.model.ArticleEntity
import org.unicef.parenthood.repository.model.TestEntity

/**
 * hub for network requests
 */
class Repository() {
    private val MAX_RANGE = 10 // maximum number of articles per feed
    private val ARTICLES_COLLECTION  = "articles"
    private val TESTS_COLLECTION  = "tests"

    private val recommendedArticles: MutableList<ArticleEntity> = mutableListOf()
    private val discoveryArticles: MutableList<ArticleEntity> = mutableListOf()

    private val urls = listOf("https://www.psychologytoday.com/intl/blog/singletons/feed",
        "https://www.janetlansbury.com/feed/")

    val firestore = Firebase.firestore

    fun getRecommended(): List<ArticleEntity> {
        firestore.collection(ARTICLES_COLLECTION)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val articleEntity = document.toObject(ArticleEntity::class.java)
                    recommendedArticles.add(articleEntity)
                }
            }
            .addOnFailureListener { exception ->
            }
        return recommendedArticles
    }

    suspend fun addTest(testEntity: TestEntity){

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
                .addOnSuccessListener { documents -> documents.first()
                }
                .addOnFailureListener { exception ->
                    //                Log.w(TAG, "Error getting documents: ", exception)
                }.result
        return result?.documents?.first()?.toObject(TestEntity::class.java)
    }

    suspend fun getDiscoverable(): List<ArticleEntity> {
        urls.forEach{
            discoveryArticles.addAll(fetchFeed(it))
        }
        return discoveryArticles
    }



    private suspend fun fetchFeed(url: String): List<ArticleEntity> {
        return withContext(Dispatchers.IO){
            val parser = Parser()
            val list: List<Article> = parser.getArticles(url)
            list.take(MAX_RANGE).map { article ->  ArticleEntity(
                id = "",
                title = article.title ?: "",
                test = null,
                content = article.content ?: "",
                categories = article.categories
            )}
        }
    }



}