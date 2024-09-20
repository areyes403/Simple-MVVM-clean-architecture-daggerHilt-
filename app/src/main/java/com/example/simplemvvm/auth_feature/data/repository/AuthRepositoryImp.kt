package com.example.simplemvvm.auth_feature.data.repository

import com.example.simplemvvm.auth_feature.data.session.SessionDataStore
import com.example.simplemvvm.auth_feature.domain.model.CurrentSession
import com.example.simplemvvm.auth_feature.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(
    private val sessionDataStore: SessionDataStore
):AuthRepository {

    override suspend fun singIn(email: String, password: String) {
        val newSession=CurrentSession(
            email = email,
            password = password
        )
        sessionDataStore.saveSession(session = newSession)
    }

    override suspend fun singOut() {
        sessionDataStore.removeSession()
    }

    override suspend fun currentSession(): Flow<CurrentSession?> = sessionDataStore.session
}