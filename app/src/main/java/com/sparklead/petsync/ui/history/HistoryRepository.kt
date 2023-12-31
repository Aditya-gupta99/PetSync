package com.sparklead.petsync.ui.history

import com.sparklead.petsync.dto.FeedDto
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {

    suspend fun feedHistory(): Flow<List<FeedDto>>

}