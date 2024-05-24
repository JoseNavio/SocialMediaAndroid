package com.navio.socialmedia.login.data.network

import android.util.Log
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository : LoginRepository){

    //To do the call later as `loginUseCase(email, password)` instead of `loginUseCase.login(email, password)`
    suspend operator fun invoke(user: String, password: String): Boolean {
        return repository.login(user, password)
    }


}