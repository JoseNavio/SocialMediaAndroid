package com.navio.socialmedia.login.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun VisibilityAnimation() {

    var isVisible by remember { mutableStateOf(true) }


    Column(Modifier.fillMaxSize()) {
        Button(onClick = { isVisible = !isVisible }) {
            Text("Show/Hide")
        }

        Spacer(modifier = Modifier.height(16.dp))

        AnimatedVisibility(isVisible) {
//        AnimatedVisibility(isVisible, enter = slideInHorizontally(), exit = slideOutHorizontally()) {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(color = Color.Red)
            )
        }
    }
}

