package com.example.simplemvvm.auth_feature.domain.repository

import com.example.simplemvvm.auth_feature.domain.model.CurrentSession
import com.example.simplemvvm.core_feature.domain.model.APIResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun singIn(credentials:CurrentSession):APIResponse<Unit>
    suspend fun singOut()
    suspend fun currentSession(): Flow<CurrentSession?>
}