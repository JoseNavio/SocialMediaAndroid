package com.navio.socialmedia.login.data.network

import android.util.Log
import com.navio.socialmedia.core.network.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginService {

    private val retrofitObject = RetrofitHelper.getRetrofitInstance()

    suspend fun login(user: String, password: String): Boolean {
        return withContext(Dispatchers.IO) {
            val response = retrofitObject.create(LoginClient::class.java).login()
            Log.i("Navio_SocialMedia", "LoginService login response: ${response.body()}")
            response.body()?.success ?: false
        }
    }
}