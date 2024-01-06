package com.ocean.whatsappclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ocean.whatsappclone.presentation.login.LoginScreen
import com.ocean.whatsappclone.ui.theme.WhatsAppCloneTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhatsAppCloneTheme {
                LoginScreen()
                /*Box(
                    modifier = Modifier.background(MaterialTheme.colorScheme.background)
                ) {
                    MenuDrawer(viewModel)
                }*/
            }
        }
    }
}