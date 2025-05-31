package com.kerimbr.kotnews.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.kerimbr.kotnews.data.model.Article
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookmarkRepository @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) {
    private val userId: String?
        get() = auth.currentUser?.uid

    private val bookmarksCollection
        get() = firestore.collection("users")
            .document(userId ?: throw IllegalStateException("User not logged in"))
            .collection("bookmarks")

    suspend fun addBookmark(article: Article): Result<Unit> {
        return try {
            bookmarksCollection
                .document(article.url)
                .set(article)
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun removeBookmark(articleUrl: String): Result<Unit> {
        return try {
            bookmarksCollection
                .document(articleUrl)
                .delete()
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getBookmarks(): Result<List<Article>> {
        return try {
            val snapshot = bookmarksCollection.get().await()
            val bookmarks = snapshot.toObjects(Article::class.java)
            Result.success(bookmarks)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun isBookmarked(articleUrl: String): Boolean {
        return try {
            val document = bookmarksCollection.document(articleUrl).get().await()
            document.exists()
        } catch (e: Exception) {
            false
        }
    }
} 