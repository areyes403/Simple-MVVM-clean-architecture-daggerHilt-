package com.example.simplemvvm.posts_feature.domain.usecase

import com.example.simplemvvm.posts_feature.domain.repository.PostsRepository
import javax.inject.Inject

class GetAllPosts @Inject constructor(
    private val repository: PostsRepository
){
    suspend operator fun invoke() = repository.getAllPost()
}