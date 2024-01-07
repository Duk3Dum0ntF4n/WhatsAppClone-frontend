package com.ocean.whatsappclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ocean.whatsappclone.presentation.MainScreen
import com.ocean.whatsappclone.presentation.MainViewModel
import com.ocean.whatsappclone.presentation.chat.ChatScreen
import com.ocean.whatsappclone.presentation.login.LoginScreen
import com.ocean.whatsappclone.ui.theme.WhatsAppCloneTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainViewModel: MainViewModel = hiltViewModel()
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
                        mainViewModel.setUsername(it.arguments?.getString("username") ?: "error")
                        MainScreen(
                            viewModel = mainViewModel,
                            onChatClicked = navController::navigate
                        )
                    }
                    composable(route = "chat_screen/{chatId}",
                        arguments = listOf(
                            navArgument(name = "chatId") {
                                type = NavType.StringType
                                nullable = true
                            }
                        )) {
                        val chatId = it.arguments?.getString("chatId") ?: ""
                        ChatScreen(
                            viewModel = mainViewModel,
                            chatId = chatId
                        )
                    }
                }
            }
        }
    }
}