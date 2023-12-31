package com.sparklead.petsync.service

import com.sparklead.petsync.dto.FeedDto
import com.sparklead.petsync.routes.HttpRoutes
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject

class FeedServiceImp @Inject constructor(private val client: HttpClient) : FeedService {

    override suspend fun getFeedHistory(): List<FeedDto> {
        return try {
            client.get {
                url(HttpRoutes.FEED_HISTORY)
                contentType(ContentType.Application.Json)
            }
        } catch (e: Exception) {
            throw e
        }
    }

}