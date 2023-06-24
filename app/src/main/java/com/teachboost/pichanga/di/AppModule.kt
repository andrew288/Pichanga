package com.teachboost.pichanga.di

import com.google.firebase.auth.FirebaseAuth
import com.teachboost.pichanga.data.auth.AuthRepository
import com.teachboost.pichanga.data.auth.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    fun providerFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun providesAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl
}