package com.example.simplemvvm.posts_feature.presenter.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplemvvm.core_feature.domain.model.UIState
import com.example.simplemvvm.core_feature.util.toUIState
import com.example.simplemvvm.posts_feature.domain.model.Posts
import com.example.simplemvvm.posts_feature.domain.usecase.GetAllPosts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllPostsUseCase: GetAllPosts
) : ViewModel() {

    private val _posts=MutableLiveData<UIState<List<Posts>>>()
    val posts:LiveData<UIState<List<Posts>>>
        get() = _posts

    init {
        getAllPosts()
    }

    private fun getAllPosts() = viewModelScope.launch(Dispatchers.IO){
        _posts.postValue(getAllPostsUseCase().toUIState())
    }
}