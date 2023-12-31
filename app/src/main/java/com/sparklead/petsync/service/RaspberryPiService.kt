package com.sparklead.petsync.service

import com.sparklead.petsync.dto.OnOffResponse

interface RaspberryPiService {

    suspend fun onOff(status: String) : OnOffResponse

}