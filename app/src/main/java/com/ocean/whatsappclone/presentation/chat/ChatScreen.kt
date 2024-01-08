package com.ocean.whatsappclone.presentation.chat

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ocean.whatsappclone.presentation.MainViewModel
import com.ocean.whatsappclone.ui.theme.WhatsAppCloneTheme
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    chatId: String,
    viewModel: MainViewModel
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.toastEvent.collectLatest { message ->
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }
    val username = viewModel.username.value
    val messages = viewModel.getChatMessages(chatId)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom
        ) {
            items(messages) { message ->
                ChatMessage(text = message.text, isMy = message.username == username)
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = viewModel.messageText.value,
                onValueChange = viewModel::onMessageChange,
                placeholder = {
                    Text(text = "Enter your message")
                },
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = viewModel::sendMessage) {
                Icon(imageVector = Icons.Default.Send, contentDescription = "Send")
            }
        }
    }

}

@Composable
private fun ChatMessage(
    text: String,
    isMy: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = if (isMy) 40.dp else 0.dp,
                end = if (isMy) 0.dp else 40.dp
            ),
        horizontalArrangement = if (isMy) Arrangement.End else Arrangement.Start
    ) {
        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(80f)
        ) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = text,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}

@Preview(
    uiMode = UI_MODE_NIGHT_NO,
    name = "LightChatScreen"
)
@Preview(
    uiMode = UI_MODE_NIGHT_YES,
    name = "DarkChatScreen"
)
@Composable
fun ChatScreenPrev() {
    WhatsAppCloneTheme {
        LazyColumn {
            items(3) {
                ChatMessage(text = "Hel", isMy = true)
            }
            items(3) {
                ChatMessage(text = "Hello", isMy = false)
            }
        }
    }
}