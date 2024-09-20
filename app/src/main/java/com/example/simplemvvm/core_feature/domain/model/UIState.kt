package com.example.simplemvvm.core_feature.domain.model

sealed class UIState<out T>{
    data class Success<out T>(val data:T):UIState<T>()
    data class Error(val message: String, val code: Int? = null) : UIState<Nothing>()
    data object Loading:UIState<Nothing>()
}