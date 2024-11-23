package com.example.socialcomposeapp.data.repository

import com.example.socialcomposeapp.data.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserRepository {
    private val db = FirebaseFirestore.getInstance()

    fun addUserToFirestore(user: UserModel, onComplete: (Boolean) -> Unit) {
        db.collection("users")
            .document(user.userId)
            .set(user)
            .addOnSuccessListener {
                onComplete(true)
            }
            .addOnFailureListener {
                onComplete(false)
            }
    }

    fun getCurrentUserFromFirestore(onComplete: (UserModel?, Boolean) -> Unit) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid

        if (userId != null) {
            db.collection("users")
                .document(userId)
                .get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {
                        // Convert document snapshot to UserModel object
                        val user = documentSnapshot.toObject(UserModel::class.java)
                        onComplete(user, true)
                    } else {
                        // Document does not exist
                        onComplete(null, false)
                    }
                }
                .addOnFailureListener {
                    onComplete(null, false)
                }
        } else {
            // No user is logged in, handle this case
            onComplete(null, false)
        }
    }

    suspend fun searchUsers(query: String): List<UserModel> {
        return try {
            val result = db.collection("users")
                .orderBy("displayName")
                .startAt(query)
                .endAt(query + "\uf8ff")
                .get()
                .await()

            result.documents.mapNotNull { it.toObject(UserModel::class.java) }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getUserById(userId: String): UserModel? {
        return try {
            val documentSnapshot = db.collection("users")
                .document(userId)
                .get()
                .await()

            if (documentSnapshot.exists()) {
                // Convert document snapshot to UserModel object
                documentSnapshot.toObject(UserModel::class.java)
            } else {
                // Document does not exist
                null
            }
        } catch (e: Exception) {
            // Handle any errors (e.g., network failures, etc.)
            null
        }
    }



    suspend fun followUser(currentUserId: String, targetUserId: String): Boolean {
        return try {
            val db = FirebaseFirestore.getInstance()

            db.collection("users")
                .document(currentUserId)
                .collection("following")
                .document(targetUserId)
                .set(mapOf("userId" to targetUserId))
                .await()

            db.collection("users")
                .document(targetUserId)
                .collection("followers")
                .document(currentUserId)
                .set(mapOf("userId" to currentUserId))
                .await()

            db.collection("users")
                .document(currentUserId)
                .update("followingCount", FieldValue.increment(1))
                .await()

            // Increment the followers count for the target user
            db.collection("users")
                .document(targetUserId)
                .update("followersCount", FieldValue.increment(1))
                .await()


            true
        } catch (e: Exception) {
            println("Error in followUser: ${e.message}")
            false
        }
    }
    suspend fun unfollowUser(currentUserId: String, targetUserId: String): Boolean {
        return try {
            val db = FirebaseFirestore.getInstance()

            db.collection("users")
                .document(currentUserId)
                .collection("following")
                .document(targetUserId)
                .delete()
                .await()

            db.collection("users")
                .document(targetUserId)
                .collection("followers")
                .document(currentUserId)
                .delete()
                .await()

            db.collection("users")
                .document(currentUserId)
                .update("followingCount", FieldValue.increment(-1))
                .await()

            // Decrement the followers count for the target user
            db.collection("users")
                .document(targetUserId)
                .update("followersCount", FieldValue.increment(-1))
                .await()


            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}