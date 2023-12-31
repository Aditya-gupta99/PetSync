package com.sparklead.petsync.ui.home

import com.sparklead.petsync.dto.OnOffDto
import kotlinx.coroutines.flow.Flow

interface RaspberryPiRepository {

    suspend fun switchOnOff(message: String): Flow<OnOffDto>

}