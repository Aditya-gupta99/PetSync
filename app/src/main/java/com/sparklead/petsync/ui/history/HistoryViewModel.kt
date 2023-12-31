package com.sparklead.petsync.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(private val repository: HistoryRepository) :
    ViewModel() {

    private val _historyUiState = MutableStateFlow<HistoryUiState>(HistoryUiState.Empty)
    val historyUiState = _historyUiState.asStateFlow()

    fun getFeedHistory() = viewModelScope.launch(Dispatchers.IO) {
        repository.feedHistory()
            .catch {
                _historyUiState.value = HistoryUiState.Error(it.message.toString())
            }
            .collect {
                _historyUiState.value = HistoryUiState.Success(it)
            }
    }

}