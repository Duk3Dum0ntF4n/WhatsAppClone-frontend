package com.ocean.whatsappclone.util.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    mainScreenContent: @Composable () -> Unit,
    callsScreenContent: @Composable () -> Unit,
    contactsScreenContent: @Composable () -> Unit,
    settingsScreenContent: @Composable () -> Unit,
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Main.route
    ) {
        composable(Screen.Main.route) {
            mainScreenContent()
        }
        composable(Screen.Calls.route) {
            callsScreenContent()
        }
        composable(Screen.Contacts.route) {
            contactsScreenContent()
        }
        composable(Screen.Settings.route) {
            settingsScreenContent()
        }
    }
}