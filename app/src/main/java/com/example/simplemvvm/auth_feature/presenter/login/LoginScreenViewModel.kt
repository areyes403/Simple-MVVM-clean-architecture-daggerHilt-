package com.example.simplemvvm.auth_feature.presenter.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplemvvm.auth_feature.domain.model.CurrentSession
import com.example.simplemvvm.auth_feature.domain.usecase.SignIn
import com.example.simplemvvm.core_feature.domain.model.APIResponse
import com.example.simplemvvm.core_feature.domain.model.UIState
import com.example.simplemvvm.core_feature.util.toUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val signInUseCase:SignIn
):ViewModel() {

    var loginResponse:UIState<Unit> by mutableStateOf(UIState.Loading)
        private set


    fun login(email:String,password:String)=viewModelScope.launch(Dispatchers.IO){
        val data=CurrentSession(email,password)
        loginResponse = try {
            delay(3000)
            signInUseCase(data).toUIState()
        }catch (e:Exception){
            UIState.Error(e.message.toString(),null)
        }

    }
}