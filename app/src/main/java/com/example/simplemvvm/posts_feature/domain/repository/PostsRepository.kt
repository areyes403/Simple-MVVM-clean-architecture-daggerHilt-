package com.example.simplemvvm.posts_feature.domain.repository

import com.example.simplemvvm.core_feature.domain.model.ResponseState
import com.example.simplemvvm.posts_feature.domain.model.Posts

interface PostsRepository {
    suspend fun getAllPost():ResponseState<List<Posts>>
}