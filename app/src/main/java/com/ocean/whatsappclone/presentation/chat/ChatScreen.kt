package com.ocean.whatsappclone.presentation.chat

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ocean.whatsappclone.domain.model.Chat
import com.ocean.whatsappclone.domain.model.Message
import com.ocean.whatsappclone.ui.theme.WhatsAppCloneTheme
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    listChat: Chat,
    allMessageContent: List<Message>,
    onBackPressed: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = listChat.username)
                },
                navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
                        Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            items(
                items = allMessageContent
            ) {
                ChatMessage(message = it, isMy = Random.nextBoolean())
            }
        }
    }
}

@Composable
private fun ChatMessage(
    message: Message,
    isMy: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = if (isMy) {
                    40.dp
                } else {
                    0.dp
                },
                end = if (isMy) {
                    0.dp
                } else {
                    40.dp
                }
            ),
        horizontalArrangement = if (isMy) {
            Arrangement.End
        } else {
            Arrangement.Start
        }
    ) {
        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(80f)
        ) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = message.text,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}

@Preview(
    uiMode = UI_MODE_NIGHT_NO,
    name = "light"
)
@Preview(
    uiMode = UI_MODE_NIGHT_YES,
    name = "dark"
)
@Composable
fun ChatScreenPrev() {
    WhatsAppCloneTheme {
        Column(
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        ) {
            ChatMessage(
                message = Message(
                    "hello","test_1", "1"
                ),
                isMy = false
            )
            ChatMessage(
                message = Message(
                    "hello","test_1", "1"
                ),
                isMy = true
            )
        }
    }
}