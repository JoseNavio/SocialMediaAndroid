package com.navio.socialmedia.login.ui

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

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
    fun onLoginValuesChanged(email: String, password: String) {
        //Set values
        _email.value = email
        _password.value = password
        //Enable or disable login button
        enableDisableLoginButton(email, password)
    }

    private fun enableDisableLoginButton(email: String,password: String){
        _isLoginEnabled.value = Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length >= 6
    }

}