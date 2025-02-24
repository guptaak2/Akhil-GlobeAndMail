package com.example.akhilglobeandmail.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.akhilglobeandmail.domain.model.GlobeAndMailResponse
import com.example.akhilglobeandmail.domain.repository.GlobeAndMailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GlobeAndMailViewModel @Inject constructor(
    private val repository: GlobeAndMailRepository
) : ViewModel() {

    private val _globeAndMailPostsState = MutableStateFlow(GlobeAndMailPostsState())
    val globeAndMailPosts = _globeAndMailPostsState.asStateFlow()

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        _globeAndMailPostsState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            when (val response = repository.fetchPosts()) {
                is GlobeAndMailResponse.Error -> {
                    _globeAndMailPostsState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = response.errorMessage.orEmpty()
                        )
                    }
                }

                is GlobeAndMailResponse.Success -> {
                    _globeAndMailPostsState.update {
                        if (response.posts.posts.isEmpty()) {
                            it.copy(
                                isLoading = false,
                                errorMessage = "No posts!"
                            )
                        } else {
                            it.copy(
                                isLoading = false,
                                postsList = response.posts.posts
                            )
                        }
                    }
                }
            }
        }
    }
}