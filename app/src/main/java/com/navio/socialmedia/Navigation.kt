package com.navio.socialmedia

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.navio.socialmedia.sealed.Routes.*

@Composable
fun Screen1(navigationController: NavHostController){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Screen 1", modifier = Modifier.align(Alignment.Center).clickable {
            navigationController.navigate(Screen2.route)
        })
    }
}

@Composable
fun Screen2(navigationController: NavHostController){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Screen 2", modifier = Modifier.align(Alignment.Center).clickable {
            navigationController.navigate(Screen3.route)
        })
    }
}

@Composable
fun Screen3(navigationController: NavHostController){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Screen 3", modifier = Modifier.align(Alignment.Center).clickable {
            navigationController.navigate(Screen4.nameOfRoute("Navio"))
        })
    }
}

@Composable
fun Screen4(navigationController: NavHostController, name: String?){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = name.orEmpty(), modifier = Modifier.align(Alignment.Center).clickable {
            navigationController.navigate(Screen5.route)
        })
    }
}

@Composable
fun Screen5(navigationController: NavHostController, name: String?){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Me llamo $name.", modifier = Modifier.align(Alignment.Center).clickable {
            navigationController.navigate(Screen1.route)
        })
    }
}
