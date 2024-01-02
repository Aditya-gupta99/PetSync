package com.sparklead.petsync.ui.home

import com.sparklead.petsync.dto.FeedDto
import com.sparklead.petsync.dto.OnOffDto

sealed class HomeUiState {

    data object Empty : HomeUiState()

    data object Loading : HomeUiState()

    data class OnOffSuccess(val onOffSuccess : OnOffDto) : HomeUiState()

    data class Error(val message : String) : HomeUiState()

    data class LatestFeed(val feedDto: FeedDto) : HomeUiState()
}