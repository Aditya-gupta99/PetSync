package com.sparklead.petsync.home

import android.util.Log
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
class HomeViewModel @Inject constructor(private val raspberryPiRepository: RaspberryPiRepository) :
    ViewModel() {

    private val _homeUiState = MutableStateFlow<HomeUiState>(HomeUiState.Empty)
    val homeUiState = _homeUiState.asStateFlow()

    fun switchOnOff(message: String) = viewModelScope.launch(Dispatchers.IO) {
        _homeUiState.value = HomeUiState.Loading
        raspberryPiRepository.switchOnOff(message)
            .catch {
                _homeUiState.value = HomeUiState.Error(it.message.toString())
                Log.e("@@@@",it.message.toString())
            }
            .collect {
                _homeUiState.value = HomeUiState.OnOffSuccess(it)
                Log.e("@@@",it.toString())
            }
    }

}