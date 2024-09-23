package com.example.simplemvvm.auth_feature.data.repository

import com.example.simplemvvm.auth_feature.data.session.SessionDataStore
import com.example.simplemvvm.auth_feature.domain.model.CurrentSession
import com.example.simplemvvm.auth_feature.domain.repository.AuthRepository
import com.example.simplemvvm.core_feature.domain.model.APIResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(
    private val sessionDataStore: SessionDataStore
):AuthRepository {

    override suspend fun singIn(credentials:CurrentSession): APIResponse<Unit> = try {
        sessionDataStore.saveSession(session = credentials)
        APIResponse.Success(Unit)
    }catch (e: HttpException) {
        APIResponse.Error("HTTP error: ${e.message}", e.code())
    } catch (e: IOException) {
        APIResponse.Error("Network error: ${e.message}", null) // O usa un código específico si aplica
    } catch (e: Exception) {
        APIResponse.Error("Unknown error: ${e.message}", null)
    }

    override suspend fun singOut() {
        sessionDataStore.removeSession()
    }

    override suspend fun currentSession(): Flow<CurrentSession?> = sessionDataStore.session
}