package com.nt.nt_notify

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.MutableState

/*
https://www.themealdb.com/api/json/v1/1/categories.php
*/



class MainViewModel: ViewModel() {
    private val _currentScreen: MutableState<Screen> = mutableStateOf(Screen.Account)
    val currentScreen: MutableState<Screen> get() = _currentScreen

    fun setCurrentScreen(screen: Screen) {
        _currentScreen.value = screen
    }

    data class TokenState(val auth: Auth? = null, val loading: Boolean = true, val error: String? = null)

    private val _authResponse = mutableStateOf(TokenState())
    val authState: State<TokenState> = _authResponse

    fun authenticate(userCredentials: UserCredentials) {
        viewModelScope.launch {
            try {
                val response = apiService.authenticate(userCredentials)
                _authResponse.value = _authResponse.value.copy(
                    auth = Auth(response.token),
                    loading = false, // Cannot find a parameter with this name: loading
                    error = null) // Cannot find a parameter with this name: error
            } catch (e: Exception) {
                _authResponse.value = _authResponse.value.copy(
                    loading = false,
                    error = "Error authenticating ${e.message}"
                )
            }
        }
    }


    data class RecipeState(
        val loading: Boolean = true,
        val list: List<Category> = emptyList(),
        val error: String? = null)

    private val _categoriesApiResponse = mutableStateOf(RecipeState())
    val categoriesState: State<RecipeState> = _categoriesApiResponse

    init {
        // fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            try {
                val response = apiService.getCategories()
                _categoriesApiResponse.value = _categoriesApiResponse.value.copy(
                    list = response.categories,
                    loading = false,
                    error = null)
            } catch (e: Exception) {
                _categoriesApiResponse.value = _categoriesApiResponse.value.copy(
                    loading = false,
                    error = "Error fetching categories ${e.message}"
                )
            }
        }
    }


}