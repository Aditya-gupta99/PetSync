package com.sparklead.petsync.home

import com.sparklead.petsync.dto.OnOffResponse

sealed class HomeUiState {

    data object Empty : HomeUiState()

    data object Loading : HomeUiState()

    data class OnOffSuccess(val onOffSuccess : OnOffResponse) : HomeUiState()

    data class Error(val message : String) : HomeUiState()
}