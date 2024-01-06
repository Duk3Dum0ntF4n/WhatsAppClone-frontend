package com.ocean.whatsappclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import com.ocean.whatsappclone.presentation.MainViewModel
import com.ocean.whatsappclone.presentation.login.LoginScreen
import com.ocean.whatsappclone.util.components.MenuDrawer
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