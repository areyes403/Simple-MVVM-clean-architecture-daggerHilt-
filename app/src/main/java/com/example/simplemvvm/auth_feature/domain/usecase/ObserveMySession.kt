package com.example.simplemvvm.auth_feature.domain.usecase

import com.example.simplemvvm.auth_feature.domain.repository.AuthRepository
import javax.inject.Inject

class ObserveMySession @Inject constructor(
    private val authRepository:AuthRepository
){
    suspend operator fun invoke()=authRepository.currentSession()
}