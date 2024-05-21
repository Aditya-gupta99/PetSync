@file:OptIn(DelicateCoroutinesApi::class)

package com.sparklead.petsync.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.sparklead.petsync.ui.home.RaspberryPiRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FeedBroadcastReceiver : BroadcastReceiver() {

    @Inject
    lateinit var raspberryPiRepository: RaspberryPiRepository

    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.e("@@@@","done")
        GlobalScope.launch {
            raspberryPiRepository.switchOnOff("false").catch {
                Log.e("@@@@",it.message.toString())
            }.collect {
                Log.e("@@@@",it.message)
            }
        }
    }
}