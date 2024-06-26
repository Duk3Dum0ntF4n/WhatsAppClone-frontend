package com.ocean.whatsappclone.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.ui.graphics.vector.ImageVector

data class Chat(
    val username: String,
    val avatar: ImageVector = Icons.Default.Face,
    val chatId: String
)
