package com.example.simplemvvm.posts_feature.util

import com.example.simplemvvm.posts_feature.data.remote.dao.PostsDao
import com.example.simplemvvm.posts_feature.domain.model.Posts

fun List<PostsDao>.toPosts(): List<Posts> {
    return this.map { it.toPost() }
}

fun PostsDao.toPost():Posts= Posts(
    id = id,
    title = title,
    body = body,
    userId = userId
)