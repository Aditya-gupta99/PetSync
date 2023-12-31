package com.sparklead.petsync.ui.home

import com.sparklead.petsync.dto.OnOffDto

sealed class HomeUiState {

    data object Empty : HomeUiState()

    data object Loading : HomeUiState()

    data class OnOffSuccess(val onOffSuccess : OnOffDto) : HomeUiState()

    data class Error(val message : String) : HomeUiState()
}