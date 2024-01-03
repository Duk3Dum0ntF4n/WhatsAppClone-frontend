package com.ocean.whatsappclone.util.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.ocean.whatsappclone.R

sealed class NavigationItem (
    val screen: Screen,
    val titleResId: Int,
    val icon: ImageVector
) {
    object Main: NavigationItem(
        screen = Screen.Main,
        titleResId = R.string.nav_main,
        icon = Icons.Outlined.Send
    )
    object Contacts: NavigationItem(
        screen = Screen.Contacts,
        titleResId = R.string.nav_contacts,
        icon = Icons.Outlined.AccountCircle
    )
    object Calls: NavigationItem(
        screen = Screen.Calls,
        titleResId = R.string.nav_calls,
        icon = Icons.Outlined.Call
    )
    object Settings: NavigationItem(
        screen = Screen.Settings,
        titleResId = R.string.nav_settings,
        icon = Icons.Outlined.Settings
    )
}