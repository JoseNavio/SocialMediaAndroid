package com.navio.socialmedia.login.ui

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.navio.socialmedia.login.data.network.LoginUseCase
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    //Email
    /** _ is used to indicate that the variable is private, it only can be modified inside this class */
    private val _email = MutableLiveData<String>()

    /** Views can read this variable, but can't modify it */
    val email: LiveData<String> = _email

    //Password
    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    //Login button
    private val _isLoginEnabled = MutableLiveData<Boolean>()
    val isLoginEnabled: LiveData<Boolean> = _isLoginEnabled

    //Loading progress...
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    fun onLoginValuesChanged(email: String, password: String) {
        //Set values
        _email.value = email
        _password.value = password
        //Enable or disable login button
        enableDisableLoginButton(email, password)
    }

    fun onLoginSelected() {

        //Calling invoke method from LoginUseCase
        viewModelScope.launch {
            _isLoading.value = true
            val result = loginUseCase(email.value ?: "", password.value ?: "")
            if (result) {
                Log.i("Navio_SocialMedia", "Login success")
            }
            _isLoading.value = false
        }
    }

    private fun enableDisableLoginButton(email: String, password: String) {
        _isLoginEnabled.value =
            Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length >= 6
    }

}