package com.navio.socialmedia

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


@Composable
fun ScaffoldScreen() {

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            ScaffoldTopAppBar() {
                coroutineScope.launch {
                    Log.d("Navio_Snack", "Launching...$it")
                    scaffoldState.snackbarHostState.showSnackbar("You clicked on $it")
                }
            }
        },
        //todo Test why it does not work with material3
        scaffoldState = scaffoldState,
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {

        }
    }
}

@Composable
fun ScaffoldTopAppBar(onClickIcon: (String) -> Unit) {
    TopAppBar(
        title = { Text("App Title") },
        backgroundColor = Color.Red,
        contentColor = Color.White, elevation = 4.dp, navigationIcon = {
            IconButton(onClick = { onClickIcon("Back") }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_arrow_back),
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        },
        actions = {
            IconButton(onClick = { onClickIcon("Add") }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_add),
                    contentDescription = "Add",
                    tint = Color.White
                )
            }
            IconButton(onClick = { onClickIcon("Delete") }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_delete),
                    contentDescription = "Delete",
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