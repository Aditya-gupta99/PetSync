package com.sparklead.petsync.service

import com.sparklead.petsync.dto.FeedDto

interface FeedService {

    suspend fun getFeedHistory(): List<FeedDto>

}