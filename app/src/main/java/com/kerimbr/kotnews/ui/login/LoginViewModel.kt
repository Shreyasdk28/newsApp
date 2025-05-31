package com.kerimbr.kotnews.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.kerimbr.kotnews.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _authState = MutableLiveData<Result<FirebaseUser>>()
    val authState: LiveData<Result<FirebaseUser>> = _authState

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = authRepository.signInWithEmail(email, password)
        }
    }

    fun register(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = authRepository.registerWithEmail(email, password)
        }
    }

    fun isUserLoggedIn(): Boolean {
        return authRepository.isUserLoggedIn()
    }
} 