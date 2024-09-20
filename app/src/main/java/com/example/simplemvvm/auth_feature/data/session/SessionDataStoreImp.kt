package com.example.simplemvvm.auth_feature.data.session

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.simplemvvm.auth_feature.domain.model.CurrentSession
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.sessionStore by preferencesDataStore(name = "session_store")

class SessionDataStoreImp @Inject constructor(
    context:Context
):SessionDataStore{
    private val sessionStore = context.sessionStore
    private val SESSION_KEY = stringPreferencesKey("session")
    private val gson = Gson()

    override val session: Flow<CurrentSession?> = sessionStore.data
        .map { preferences ->
            preferences[SESSION_KEY]?.let { json ->
                gson.fromJson(json, CurrentSession::class.java)
            }
        }
    override suspend fun saveSession(session: CurrentSession) {
        val json = gson.toJson(session)
        sessionStore.edit { preferences ->
            preferences[SESSION_KEY] = json
        }
    }
    override suspend fun removeSession() {
        sessionStore.edit { preferences ->
            preferences.remove(SESSION_KEY)
        }
    }
}