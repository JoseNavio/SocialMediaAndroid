package com.navio.socialmedia

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun ScaffoldScreen() {
    Scaffold(
        topBar = { ScaffoldTopAppBar() }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {

        }
    }
}

@Composable
fun ScaffoldTopAppBar() {
    TopAppBar(
        title = { Text("App Title") },
        backgroundColor = Color.Red,
        contentColor = Color.White, elevation = 4.dp, navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_arrow_back),
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ScaffoldPreview() {
    ScaffoldScreen()
}