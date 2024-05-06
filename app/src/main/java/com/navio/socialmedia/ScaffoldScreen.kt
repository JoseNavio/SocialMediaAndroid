package com.navio.socialmedia

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FabPosition
import androidx.compose.material3.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
        scaffoldState = scaffoldState,
        topBar = {
            ScaffoldTopAppBar(
                onClickIcon = {
                    coroutineScope.launch {
                        Log.d("Navio_Snack", "Launching...$it")
                        scaffoldState.snackbarHostState.showSnackbar("You clicked on $it")
                    }
                },
                onClickDrawer = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.open()
                    }
                })
        },
        //todo Test why it does not work with material3
        bottomBar = { MyBottomNavigation() },
        drawerContent = {
            MyDrawer(onCloseDrawer = {
                coroutineScope.launch {
                    scaffoldState.drawerState.close()
                }
            })
        },
        //To drag the drawer from the edge set it to true
        drawerGesturesEnabled = false,
        floatingActionButton = { MyFAB() },
        //This below are default values
        floatingActionButtonPosition = FabPosition.End,
        isFloatingActionButtonDocked = false
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {

        }
    }
}

@Composable
fun ScaffoldTopAppBar(onClickIcon: (String) -> Unit, onClickDrawer: () -> Unit) {
    TopAppBar(
        title = { Text("App Title") },
        backgroundColor = Color.Gray,
        contentColor = Color.White, elevation = 4.dp, navigationIcon = {
            IconButton(onClick = { onClickDrawer() }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu",
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

@Composable
fun MyFAB() {
    FloatingActionButton(
        onClick = { /*TODO*/ },
        containerColor = Color.Gray,
        contentColor = Color.White
    ) {
        Icon(painter = painterResource(id = R.drawable.icon_add), contentDescription = "Add")
    }
}

@Composable
fun MyDrawer(onCloseDrawer: () -> Unit) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            text = "Primero", modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { onCloseDrawer() }
        )
        Text(
            text = "Segundo", modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { onCloseDrawer() }
        )
        Text(
            text = "Tercero", modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { onCloseDrawer() }
        )
        Text(
            text = "Cuarto", modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { onCloseDrawer() }
        )
    }
}

//Bottom Bar
@Composable
fun MyBottomNavigation() {
    var selectedIndex by remember { mutableIntStateOf(0) }
    BottomNavigation(backgroundColor = Color.Gray, contentColor = Color.White) {
        BottomNavigationItem(
            selected = selectedIndex == 0,
            onClick = { selectedIndex = 0 },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    tint = Color.White
                )
            },
            label = { Text("Home") }
        )
        BottomNavigationItem(
            selected = selectedIndex == 1,
            onClick = { selectedIndex = 1 },
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite",
                    tint = Color.White
                )
            },
            label = { Text("Favorite") }
        )
        BottomNavigationItem(
            selected = selectedIndex == 2,
            onClick = { selectedIndex = 2 },
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Person",
                    tint = Color.White
                )
            },
            label = { Text("Person") }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ScaffoldPreview() {
    ScaffoldScreen()
}

