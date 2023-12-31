package com.sparklead.petsync.service

import com.sparklead.petsync.dto.OnOffDto

interface RaspberryPiService {

    suspend fun onOff(status: String) : OnOffDto

}