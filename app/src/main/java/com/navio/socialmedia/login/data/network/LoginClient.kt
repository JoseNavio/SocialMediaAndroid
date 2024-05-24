package com.navio.socialmedia.login.data.network

import com.navio.socialmedia.login.data.network.responses.LoginResponse
import retrofit2.Response
import retrofit2.http.GET

interface LoginClient {
    @GET("/v3/770d0928-7c85-4e04-a87c-093b054fcf44")
    suspend fun login(): Response<LoginResponse>
}