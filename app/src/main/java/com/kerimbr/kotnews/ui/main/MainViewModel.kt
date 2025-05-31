package com.kerimbr.kotnews.ui.main

import androidx.lifecycle.ViewModel
import com.kerimbr.kotnews.data.model.User
import com.kerimbr.kotnews.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    val currentUser: User?
        get() = authRepository.getCurrentUserModel()

    fun signOut() {
        authRepository.signOut()
    }
} 