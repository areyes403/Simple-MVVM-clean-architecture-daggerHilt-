package com.example.simplemvvm.core_feature.util

import com.example.simplemvvm.core_feature.domain.model.ResponseState
import com.example.simplemvvm.core_feature.domain.model.UIState
import com.example.simplemvvm.posts_feature.data.remote.dao.PostsDao
import com.example.simplemvvm.posts_feature.domain.model.Posts

fun PostsDao.toPost():Posts= Posts(
    id = id,
    title = title,
    body = body,
    userId = userId
)

fun <T> ResponseState<T>.toUIState(): UIState<T> {
    return when(this){
        is ResponseState.Success-> UIState.Success(data)
        is ResponseState.Error-> UIState.Failure(error)
    }
}
