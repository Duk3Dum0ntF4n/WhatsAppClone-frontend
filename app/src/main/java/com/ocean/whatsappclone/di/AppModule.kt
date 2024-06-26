package com.ocean.whatsappclone.di

import androidx.hilt.navigation.compose.hiltViewModel
import com.ocean.whatsappclone.data.remote.service.MessengerSocketService
import com.ocean.whatsappclone.data.remote.service.MessengerSocketServiceImpl
import com.ocean.whatsappclone.presentation.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.websocket.WebSockets
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient() : HttpClient {
        return HttpClient(CIO) {
            install(Logging)
            install(WebSockets)
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
        }
    }

    @Provides
    @Singleton
    fun provideMessengerSocketService(client: HttpClient): MessengerSocketService {
        return MessengerSocketServiceImpl(client)
    }
}