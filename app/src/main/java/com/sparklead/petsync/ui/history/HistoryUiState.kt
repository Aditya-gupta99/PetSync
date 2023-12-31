package com.sparklead.petsync.ui.history

import com.sparklead.petsync.dto.FeedDto

sealed class HistoryUiState {

    data object Empty : HistoryUiState()

    data object Loading : HistoryUiState()

    data class Success(val list: List<FeedDto>) : HistoryUiState()

    data class Error(val message: String) : HistoryUiState()

}