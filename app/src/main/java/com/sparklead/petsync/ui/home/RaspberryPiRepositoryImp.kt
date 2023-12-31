package com.sparklead.petsync.ui.home

import com.sparklead.petsync.dto.OnOffDto
import com.sparklead.petsync.service.RaspberryPiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RaspberryPiRepositoryImp @Inject constructor(private val raspberryPiService: RaspberryPiService) :
    RaspberryPiRepository {

    override suspend fun switchOnOff(message: String): Flow<OnOffDto> {
        return flow {
            emit(raspberryPiService.onOff(message))
        }
    }
}