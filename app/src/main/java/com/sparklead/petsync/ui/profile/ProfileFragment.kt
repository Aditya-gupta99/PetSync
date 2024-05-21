package com.sparklead.petsync.ui.profile

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.sparklead.petsync.databinding.FragmentProfileBinding
import com.sparklead.petsync.receiver.FeedBroadcastReceiver
import java.util.Calendar

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ProfileScreen()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setAlarm() {
        Log.e("@@@@", "come")
        val alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmIntent = Intent(requireContext(), FeedBroadcastReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            requireContext(),
            0,
            alarmIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        // Set the alarm to start at 10:00 AM every day
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 11)
            set(Calendar.MINUTE, 37)
            set(Calendar.SECOND, 0)
        }

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }

    fun offAlarm() {
        // Step 1: Retrieve the PendingIntent
        val alarmIntent = Intent(requireContext(), FeedBroadcastReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(requireContext(), 0, alarmIntent, PendingIntent.FLAG_IMMUTABLE)

        // Step 2: Cancel the PendingIntent
        val alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pendingIntent)

        // Optionally, you may want to cancel any pending operations associated with the PendingIntent
        pendingIntent.cancel()

    }
}