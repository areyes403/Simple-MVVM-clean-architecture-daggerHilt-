package com.example.simplemvvm.posts_feature.data.remote

import com.example.simplemvvm.posts_feature.data.remote.dao.PostsDao
import retrofit2.Response
import retrofit2.http.GET

interface PostsApiService {
    @GET("posts")
    suspend fun getAllPosts():Response<List<PostsDao>>
}