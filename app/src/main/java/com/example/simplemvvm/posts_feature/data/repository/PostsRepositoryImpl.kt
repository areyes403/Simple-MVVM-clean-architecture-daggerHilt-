package com.example.simplemvvm.posts_feature.data.repository

import com.example.simplemvvm.core_feature.domain.model.APIResponse
import com.example.simplemvvm.posts_feature.data.remote.PostsApiService
import com.example.simplemvvm.posts_feature.domain.model.Posts
import com.example.simplemvvm.posts_feature.domain.repository.PostsRepository
import com.example.simplemvvm.posts_feature.util.toPosts
import retrofit2.Response
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val postsApiService: PostsApiService
):PostsRepository {

    override suspend fun getAllPost(): APIResponse<List<Posts>> {
        return safeApiCall {
            postsApiService.getAllPosts()
        }.let { response ->
            when (response) {
                is APIResponse.Success -> APIResponse.Success(response.data.toPosts())
                is APIResponse.Error -> response
            }
        }
    }

    private suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): APIResponse<T> {
        return try {
            val response = apiCall()
            if (response.isSuccessful) {
                response.body()?.let {
                    APIResponse.Success(it)
                } ?: APIResponse.Error("Empty Response", response.code())
            } else {
                APIResponse.Error(response.errorBody()?.string(), response.code())
            }
        } catch (e: Exception) {
            APIResponse.Error(e.message, null)
        }
    }

}