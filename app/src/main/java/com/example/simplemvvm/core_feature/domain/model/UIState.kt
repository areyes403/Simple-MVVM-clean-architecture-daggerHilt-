package com.example.simplemvvm.core_feature.domain.model

sealed class UIState<out T>{
    data class Success<out T>(val data:T):UIState<T>()
    data class Failure (val error:String?):UIState<Nothing>()
    data object Loading:UIState<Nothing>()

}