package com.teachboost.pichanga.data.auth

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    suspend fun signup(name: String, lastname: String, email: String, password: String): Resource<FirebaseUser>
    fun logout()
}