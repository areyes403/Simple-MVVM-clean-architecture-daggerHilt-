package com.example.simplemvvm.posts_feature.data.repository

import com.example.simplemvvm.core_feature.domain.model.ResponseState
import com.example.simplemvvm.core_feature.util.toPost
import com.example.simplemvvm.posts_feature.data.remote.PostsApiService
import com.example.simplemvvm.posts_feature.domain.model.Posts
import com.example.simplemvvm.posts_feature.domain.repository.PostsRepository
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val postsApiService: PostsApiService
):PostsRepository {
    override suspend fun getAllPost(): ResponseState<List<Posts>> {
        return try {
            val response=postsApiService.getAllPosts()
            if(response.isSuccessful){
                val list= mutableListOf<Posts>()
                response.body()?.forEach {
                    list.add(it.toPost())
                }
                ResponseState.Success(list)
            }else{
                ResponseState.Error(response.errorBody()?.string())
            }
        }catch (e:Exception){
            ResponseState.Error(e.message)
        }
    }

}