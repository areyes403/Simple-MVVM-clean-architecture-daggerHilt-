package com.example.simplemvvm.core_feature.presenter.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplemvvm.auth_feature.domain.model.CurrentSession
import com.example.simplemvvm.auth_feature.domain.usecase.ObserveMySession
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val observeMySessionUseCase: ObserveMySession
):ViewModel() {
    private val _mySession= MutableStateFlow<CurrentSession?>(null)
    val mySession:StateFlow<CurrentSession?> get() = _mySession
        .onStart {
            startObservingSession()
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            null
        )


    private fun startObservingSession()=viewModelScope.launch(Dispatchers.IO){
        observeMySessionUseCase().collect{
            _mySession.value=it
        }
    }

}