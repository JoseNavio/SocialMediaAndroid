package com.navio.socialmedia.login.data.network

import android.util.Log
import javax.inject.Inject

/** We uses repository pattern to abstract the data source from the view model
 *
 * For example, the view model doesn't need to know if the data is coming from a network request or
 * a local database.
 *
 */
class LoginRepository @Inject constructor(private val api : LoginService){

    suspend fun login(user: String, password: String) :Boolean {
        return api.login(user, password)
    }
}