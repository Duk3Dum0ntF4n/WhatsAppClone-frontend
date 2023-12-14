package com.ocean.whatsappclone.domain

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.ui.graphics.vector.ImageVector

data class ListChat(
    val chat_id: Int,
    val prev_text: String = "Lorem ipsum dolor sit amet",
    val author: String = "eneguE",
    val prev_time: String = "00:00",
    val avatar: ImageVector = Icons.Default.Face
)
