package com.example.simplemvvm.posts_feature.di

import com.example.simplemvvm.posts_feature.data.remote.PostsApiService
import com.example.simplemvvm.posts_feature.data.repository.PostsRepositoryImpl
import com.example.simplemvvm.posts_feature.domain.repository.PostsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface PostsModule {
    @Binds
    @Singleton
    fun bindsPostsModule(
        postsRepository: PostsRepositoryImpl
    ):PostsRepository

    companion object{
        @Provides
        @Singleton
        fun providePostsService(
          retrofit: Retrofit
        ):PostsApiService= retrofit.create(PostsApiService::class.java)
    }
}