package com.example.simplemvvm.core_feature.domain.model

sealed class ResponseState <out T>{
    data class Success <out T>(val data:T):ResponseState<T>()
    data class Error (val error:String?):ResponseState<Nothing>()
}