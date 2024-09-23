package com.example.simplemvvm.auth_feature.domain.usecase

import com.example.simplemvvm.auth_feature.domain.model.CurrentSession
import com.example.simplemvvm.auth_feature.domain.repository.AuthRepository
import com.example.simplemvvm.core_feature.domain.model.APIResponse
import javax.inject.Inject

class SignIn @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(userData:CurrentSession):APIResponse<Unit> = authRepository.singIn(credentials=userData)
}