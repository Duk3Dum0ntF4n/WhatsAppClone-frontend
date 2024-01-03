package com.ocean.whatsappclone.util.navigation

sealed class Screen (
    val route: String
) {
    object Main: Screen(ROUTE_MAIN)
    object Contacts: Screen(ROUTE_CONTACTS)
    object Calls: Screen(ROUTE_CALLS)
    object Settings: Screen(ROUTE_SETTINGS)

    private companion object {
        const val ROUTE_MAIN = "main"
        const val ROUTE_CONTACTS = "contacts"
        const val ROUTE_CALLS = "calls"
        const val ROUTE_SETTINGS = "settings"
    }
}
