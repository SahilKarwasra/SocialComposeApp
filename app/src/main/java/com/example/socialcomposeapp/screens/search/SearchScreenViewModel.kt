@file:OptIn(FlowPreview::class)

package com.example.socialcomposeapp.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialcomposeapp.data.model.UserModel
import com.example.socialcomposeapp.data.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchScreenViewModel(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText

    private var currentUserId = ""

    private val _searchResults = MutableStateFlow<List<UserModel>>(emptyList())
    val searchResults = searchText
        .debounce( 300L)
        .combine(_searchResults){ searchText,searchResults ->
            if (searchText.isBlank()){
                searchResults
            }else {
                performSearch(searchText) ?: emptyList()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), emptyList())

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage




    fun onSearchTextChange(query: String) {
        _searchText.value = query
    }

    private suspend fun performSearch(query: String):List<UserModel>? = withContext(Dispatchers.IO)  {
        return@withContext try {
            _isLoading.value = true
            _errorMessage.value = null
            userRepository.searchUsers(query)

        } catch (e: Exception) {
            _errorMessage.value = "Error: ${e.message}"
            null
        } finally {
            _isLoading.value = false
        }
    }
}
