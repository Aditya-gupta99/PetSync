package com.sparklead.petsync.home

import com.sparklead.petsync.dto.OnOffResponse
import com.sparklead.petsync.service.RaspberryPiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RaspberryPiRepositoryImp @Inject constructor(private val raspberryPiService: RaspberryPiService): RaspberryPiRepository {

    override suspend fun switchOnOff(message: String): Flow<OnOffResponse> {
        return flow {
            emit(raspberryPiService.onOff(message))
        }
    }
}