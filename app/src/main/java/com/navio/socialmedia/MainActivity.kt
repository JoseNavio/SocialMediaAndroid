package com.navio.socialmedia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.navio.socialmedia.login.ui.LoginScreen
import com.navio.socialmedia.login.ui.LoginViewModel
import com.navio.socialmedia.ui.theme.SocialMediaTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SocialMediaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
//                    color = Color(0xFF15202B)
                    color = Color.White
                ) {
                    LoginScreen(LoginViewModel()) { finish() }

//                    ScaffoldScreen()

                    //Navigation Controller
//                    val navigationController = rememberNavController()
//                    NavHost(
//                        navController = navigationController,
//                        startDestination = Routes.Screen1.route
//                    ) {
//                        composable(route = Routes.Screen1.route) { Screen1(navigationController) }
//                        composable(route = Routes.Screen2.route) { Screen2(navigationController) }
//                        composable(route = Routes.Screen3.route) { Screen3(navigationController) }
//
//                        //Arguments
//                        composable(
//                            route = Routes.Screen4.route,
//                            arguments = listOf(navArgument("name") { type = NavType.StringType })
//                        ) { backStackEntry ->
//                            Screen4(
//                                navigationController,
//                                backStackEntry.arguments?.getString("name")
//                            )
//                        }
//                        //Optional parameters in the function...
//                        composable(
//                            route = Routes.Screen5.route, arguments = listOf(
//                                navArgument(
//                                    "name",
//                                ) { defaultValue = "Pepe" }
//                            )
//                        ) { backStackEntry ->
//                            Screen5(
//                                navigationController,
//                                backStackEntry.arguments?.getString("name")
//                            )
//                        }
//                    }
                }
            }
        }


    }
}

@Composable
fun BlueBox() {

    Box(
        modifier = Modifier
            .size(200.dp)
            .background(color = Color.Blue),
        contentAlignment = Alignment.TopStart
    ) {
        TweetScreenConstraint()
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF15202B)
    ) {
        LoginScreen(LoginViewModel()) { }
    }
}