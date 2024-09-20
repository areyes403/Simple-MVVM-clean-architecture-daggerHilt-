package com.example.simplemvvm.core_feature.util

import com.example.simplemvvm.core_feature.domain.model.APIResponse
import com.example.simplemvvm.core_feature.domain.model.UIState

fun <T> APIResponse<T>.toUIState(): UIState<T> {
    return when (this) {
        is APIResponse.Success -> UIState.Success(data)
        is APIResponse.Error -> UIState.Error(message ?: "Unknown error occurred", code)
    }
}