package com.ocean.whatsappclone.util.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ocean.whatsappclone.presentation.MainViewModelOld
import com.ocean.whatsappclone.util.navigation.AppNavGraph
import com.ocean.whatsappclone.util.navigation.NavigationItem
import com.ocean.whatsappclone.util.navigation.rememberNavigationState
import com.ocean.whatsappclone.presentation.calls.CallsScreen
import com.ocean.whatsappclone.presentation.contacts.ContactsScreen
import com.ocean.whatsappclone.presentation.chat_list.ListChatsScreen
import com.ocean.whatsappclone.presentation.settings.SettingsScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuDrawer(
    viewModel: MainViewModelOld
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navigationState = rememberNavigationState()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                val items = listOf(
                    NavigationItem.Main,
                    NavigationItem.Contacts,
                    NavigationItem.Calls,
                    NavigationItem.Settings
                )
                DrawerMenuHeader()
                items.forEach { item ->
                    NavigationDrawerItem(
                        label = {
                            Text(text = stringResource(id = item.titleResId))
                        },
                        icon = {
                            Icon(imageVector = item.icon, contentDescription = null)
                        },
                        selected = currentRoute == item.screen.route,
                        onClick = {
                            scope.launch {
                                navigationState.navigateTo(item.screen.route)
                                drawerState.close()
                            }
                        }
                    )
                }
            }
        }
    ) {
        AppNavGraph(
            navHostController = navigationState.navHostController,
            mainScreenContent = {
                ListChatsScreen(scope, drawerState, viewModel)
            },
            callsScreenContent = {
                CallsScreen()
            },
            contactsScreenContent = {
                ContactsScreen()
            },
            settingsScreenContent = {
                SettingsScreen()
            }
        )
    }
}

@Composable
private fun DrawerMenuHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.End
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        )
        {
            Icon(
                modifier = Modifier
                    .padding(20.dp)
                    .size(50.dp),
                imageVector = Icons.Outlined.Face, contentDescription = null
            )
            Text(
                modifier = Modifier.padding(start = 20.dp),
                text = "eneguE"
            )
        }
        IconButton(onClick = {

        }) {
            Icon(
                modifier = Modifier
                    .padding(20.dp)
                    .size(30.dp),
                imageVector = Icons.Outlined.Star,
                contentDescription = null
            )
        }
    }
}