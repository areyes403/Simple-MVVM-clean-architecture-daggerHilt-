package com.example.simplemvvm.auth_feature.di

import android.content.Context
import com.example.simplemvvm.auth_feature.data.repository.AuthRepositoryImp
import com.example.simplemvvm.auth_feature.data.session.SessionDataStore
import com.example.simplemvvm.auth_feature.data.session.SessionDataStoreImp
import com.example.simplemvvm.auth_feature.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AuthModule {

    @Binds
    @Singleton
    fun bindAuthRepository(
        authRepositoryImp: AuthRepositoryImp
    ):AuthRepository

    companion object{
        @Provides
        @Singleton
        fun provideSessionDataStore(@ApplicationContext context: Context): SessionDataStore =
            SessionDataStoreImp(context = context)
    }
}