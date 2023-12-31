package com.sparklead.petsync.home

import com.sparklead.petsync.dto.OnOffResponse
import kotlinx.coroutines.flow.Flow

interface RaspberryPiRepository {

    suspend fun switchOnOff(message : String) : Flow<OnOffResponse>

}