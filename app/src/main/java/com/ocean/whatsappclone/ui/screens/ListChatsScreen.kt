package com.ocean.whatsappclone.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ocean.whatsappclone.MainViewModel
import com.ocean.whatsappclone.domain.ListChat
import com.ocean.whatsappclone.ui.ListScreenState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListChatsScreen(
    scope: CoroutineScope,
    drawerState: DrawerState,
    viewModel: MainViewModel
) {
    //Создай стейт где будешь хранить состояние scaffold
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    }) {
                        Icon(imageVector = Icons.Outlined.Menu, contentDescription = null)
                    }
                },
                title = {
                    Text(text = "WhatsAppClone")
                }
            )
        }
    ) { paddingValues ->
        val screenState = viewModel.screenState.observeAsState(ListScreenState.Initial)
        when (val currentState = screenState.value) {
            is ListScreenState.Chats -> {
                ChatLists(
                    viewModel = viewModel,
                    chats = currentState.chats,
                    paddingValues = paddingValues
                )
            }

            is ListScreenState.Messages -> {
                ChatScreen(
                    listChat = currentState.chat,
                    allMessageContent = currentState.messages,
                    onBackPressed = {
                        viewModel.closeComments()
                    }
                )
            }

            ListScreenState.Initial -> {

            }
        }
    }
}

@Composable
private fun ChatLists(
    viewModel: MainViewModel,
    chats: List<ListChat>,
    paddingValues: PaddingValues,
) {
    LazyColumn(
        modifier = Modifier.padding(paddingValues)
    ) {
        items(items = chats, key = { it.chat_id }) {
            ListItem(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(10.dp)
                    .fillMaxWidth()
                    .height(80.dp),
                chat = it,
                onChatClickListener = {
                    viewModel.showChatMessages(it)
                }
            )
        }
    }
}

@Composable
private fun ListItem(
    modifier: Modifier = Modifier,
    chat: ListChat,
    onChatClickListener: () -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize().clickable{ onChatClickListener() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Spacer(modifier = Modifier.width(10.dp))
            Image(
                modifier = Modifier.size(50.dp),
                imageVector = Icons.Outlined.AccountCircle,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = chat.author,
                    fontSize = 24.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = chat.prev_text,
                    fontSize = 16.sp
                )
            }
            Text(text = chat.prev_time)
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}