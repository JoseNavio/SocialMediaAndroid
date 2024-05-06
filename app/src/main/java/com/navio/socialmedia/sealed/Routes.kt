package com.navio.socialmedia.sealed

sealed class Routes(val route:String) {
    data object Screen1: Routes("pantalla1")
    data object Screen2: Routes("pantalla2")
    data object Screen3: Routes("pantalla3")
    data object Screen4: Routes("pantalla4/{name}"){
        fun nameOfRoute(name: String) = "pantalla4/$name"
    }
    //Optional parameters in the function...
    data object Screen5: Routes("pantalla5?name={name}"){
        fun nameOfRoute(name: String) = "pantalla5?name=$name"
    }
}