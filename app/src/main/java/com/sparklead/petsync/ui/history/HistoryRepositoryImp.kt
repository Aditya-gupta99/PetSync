package com.sparklead.petsync.ui.history

import com.sparklead.petsync.dto.FeedDto
import com.sparklead.petsync.service.FeedService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HistoryRepositoryImp @Inject constructor(private val feedService: FeedService) :
    HistoryRepository {

    override suspend fun feedHistory(): Flow<List<FeedDto>> {
        return flow {
            emit(feedService.getFeedHistory())
        }
    }
}