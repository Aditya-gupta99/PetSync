package com.sparklead.petsync.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparklead.petsync.ui.history.HistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val raspberryPiRepository: RaspberryPiRepository,
    private val historyRepository: HistoryRepository
) :
    ViewModel() {

    private val _homeUiState = MutableStateFlow<HomeUiState>(HomeUiState.Empty)
    val homeUiState = _homeUiState.asStateFlow()

    fun switchOnOff(message: String) = viewModelScope.launch(Dispatchers.IO) {
        _homeUiState.value = HomeUiState.Loading
        raspberryPiRepository.switchOnOff(message)
            .catch {
                _homeUiState.value = HomeUiState.Error(it.message.toString())
            }
            .collect {
                _homeUiState.value = HomeUiState.OnOffSuccess(it)
            }
    }

    fun getLatestFeed() = viewModelScope.launch(Dispatchers.IO) {
        historyRepository.feedHistory()
            .catch {
                _homeUiState.value = HomeUiState.Error(it.message.toString())
            }
            .collect {
                if (it.isNotEmpty()) {
                    _homeUiState.value =
                        HomeUiState.LatestFeed(it.sortedByDescending { it1 -> it1.timestamp }[0])
                } else {
                    _homeUiState.value = HomeUiState.Error("No previous Data")
                }
            }
    }

}