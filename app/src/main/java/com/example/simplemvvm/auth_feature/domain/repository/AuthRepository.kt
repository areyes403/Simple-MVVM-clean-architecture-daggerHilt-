package com.example.simplemvvm.auth_feature.domain.repository

import com.example.simplemvvm.auth_feature.domain.model.CurrentSession
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun singIn(email:String,password:String)
    suspend fun singOut()
    suspend fun currentSession(): Flow<CurrentSession?>
}