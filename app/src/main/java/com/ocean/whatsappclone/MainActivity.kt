package com.ocean.whatsappclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ocean.whatsappclone.presentation.MainScreen
import com.ocean.whatsappclone.presentation.login.LoginScreen
import com.ocean.whatsappclone.ui.theme.WhatsAppCloneTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhatsAppCloneTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "login_screen") {
                    composable("login_screen") {
                        LoginScreen(onNavigate = navController::navigate)
                    }
                    composable(route = "main_screen/{username}",
                        arguments = listOf(
                            navArgument(name = "username") {
                                type = NavType.StringType
                                nullable = true
                            }
                        )
                    ) {
                        val username = it.arguments?.getString("username")
                        MainScreen(username)
                    }
                }
            }
        }
    }
}