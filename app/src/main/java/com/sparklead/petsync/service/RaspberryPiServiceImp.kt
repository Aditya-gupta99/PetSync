package com.sparklead.petsync.service

import com.sparklead.petsync.dto.OnOffResponse
import com.sparklead.petsync.routes.HttpRoutes
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject

class RaspberryPiServiceImp @Inject constructor(private val client: HttpClient) : RaspberryPiService {

    override suspend fun onOff(status : String): OnOffResponse {
        return try {
            client.post{
                url(HttpRoutes.ON_OFF)
                contentType(ContentType.Application.Json)
                body = status
            }
        } catch (e : Exception) {
            throw e
        }
    }

}