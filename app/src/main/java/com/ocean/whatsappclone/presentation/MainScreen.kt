package com.ocean.whatsappclone.presentation

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.ocean.whatsappclone.domain.model.Chat
import com.ocean.whatsappclone.ui.theme.WhatsAppCloneTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MainScreen(
    username: String?, viewModel: MainViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.toastEvent.collectLatest { message ->
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                viewModel.connect()
            } else if (event == Lifecycle.Event.ON_STOP) {
                viewModel.disconnect()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    val state = viewModel.state.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            reverseLayout = true
        ) {
            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
            items(state.chats) { chat ->
                ChatElement(chat)
            }
        }
    }
}

@Composable
fun ChatElement(
    chat: Chat,
) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
            .clickable {
                TODO("Add click listener here")
            },
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                modifier = Modifier.size(50.dp),
                imageVector = chat.avatar,
                contentDescription = "Chat avatar"
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = chat.username,
                    fontSize = 24.sp
                )
            }
        }
    }
}

@Preview(
    uiMode = UI_MODE_NIGHT_NO,
    name = "Light"
)
@Preview(
    uiMode = UI_MODE_NIGHT_YES,
    name = "Dark"
)
@Composable
fun MainScreenPrev() {
    WhatsAppCloneTheme {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(3) {
                ChatElement(chat = Chat(username = "test", chatId = "1"))
            }
        }
    }
}