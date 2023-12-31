package com.sparklead.petsync.di

import com.sparklead.petsync.service.FeedService
import com.sparklead.petsync.service.FeedServiceImp
import com.sparklead.petsync.service.RaspberryPiService
import com.sparklead.petsync.service.RaspberryPiServiceImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.HttpTimeout
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.websocket.WebSockets
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationComponent {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(Android) {
            install(WebSockets)
            install(JsonFeature) {
                serializer = KotlinxSerializer(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 35000
            }
            install(Logging) {
                level = LogLevel.ALL
            }
        }
    }

    @Provides
    @Singleton
    fun providesRaspberryPiService(client: HttpClient): RaspberryPiService =
        RaspberryPiServiceImp(client)

    @Provides
    @Singleton
    fun providesFeedService(client: HttpClient): FeedService =
        FeedServiceImp(client)
}