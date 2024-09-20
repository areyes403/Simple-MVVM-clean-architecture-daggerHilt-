package com.example.simplemvvm.auth_feature.data.session

import com.example.simplemvvm.auth_feature.domain.model.CurrentSession
import kotlinx.coroutines.flow.Flow

interface SessionDataStore {
    val session: Flow<CurrentSession?>
    suspend fun saveSession(session: CurrentSession)
    suspend fun removeSession()
}