package com.example.simplemvvm.core_feature.domain.model

sealed class APIResponse<out T> {
    data class Success<out T>(val data: T) : APIResponse<T>()
    data class Error(val message: String?, val code: Int?) : APIResponse<Nothing>()
}
