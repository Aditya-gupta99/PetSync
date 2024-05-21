package com.sparklead.petsync.ui.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    var checked1 by mutableStateOf(false)
    var checked2 by mutableStateOf(false)
    var checked3 by mutableStateOf(false)

    var alarmTime1 by mutableStateOf("9:00 AM")
    var alarmTime2 by mutableStateOf("2:00 PM")
    var alarmTime3 by mutableStateOf("8:00 PM")


}