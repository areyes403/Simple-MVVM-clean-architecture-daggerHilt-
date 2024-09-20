package com.example.simplemvvm.posts_feature.domain.repository

import com.example.simplemvvm.core_feature.domain.model.APIResponse
import com.example.simplemvvm.posts_feature.domain.model.Posts

interface PostsRepository {
    suspend fun getAllPost():APIResponse<List<Posts>>
}