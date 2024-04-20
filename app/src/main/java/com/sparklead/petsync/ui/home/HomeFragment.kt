package com.sparklead.petsync.ui.home

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.aar.tapholdupbutton.TapHoldUpButton
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.sparklead.petsync.databinding.FragmentHomeBinding
import com.sparklead.petsync.dto.FeedDto
import com.sparklead.petsync.firebase.FirebaseService
import com.sparklead.petsync.utils.Constants
import com.zegocloud.uikit.prebuilt.call.config.ZegoNotificationConfig
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var status = false
    private var token = ""
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        fcmSetup()
        videoCallServices("6969")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.homeUiState.collect {
                when (it) {
                    is HomeUiState.Empty -> {}

                    is HomeUiState.Error -> {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }

                    is HomeUiState.Loading -> {}

                    is HomeUiState.OnOffSuccess -> {
                        Toast.makeText(
                            requireContext(),
                            it.onOffSuccess.toString(),
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    is HomeUiState.LatestFeed -> {
                        onLatestFeedSuccess(it.feedDto)
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            while (isActive) {
                viewModel.getLatestFeed()
                delay(6000)
            }
        }


        binding.btnStart.setOnButtonClickListener(object : TapHoldUpButton.OnButtonClickListener {
            override fun onLongHoldStart(v: View?) {
                viewModel.switchOnOff(status.toString())
                status = !status
                Toast.makeText(
                    requireContext(),
                    if (status) "Pet feeder started" else "Pet feeder off",
                    Toast.LENGTH_LONG
                ).show()
                if (status) binding.tvShowStatus.text = "Online" else binding.tvShowStatus.text =
                    "Offline"
            }

            override fun onLongHoldEnd(v: View?) {
            }

            override fun onClick(v: View?) {
                binding.tvBtnInfo.text = "Hold to Start"
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.tvBtnInfo.text = ""
                }, 1000)
            }
        })

    }

    private fun onLatestFeedSuccess(it: FeedDto) {
        binding.tvPetName.text = it.pet
        binding.tvFeedDate.text = Constants.convertTimestampToDate(it.timestamp).first
        binding.tvTimeAgo.text = Constants.timeDifference(it.timestamp)
    }


    private fun fcmSetup() {

        FirebaseService.sharedPref =
            requireContext().getSharedPreferences(Constants.SHARED_PREFERENCE, Context.MODE_PRIVATE)
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("Fcm token", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            token = task.result
            Log.e("token", token)
            // subscribing fcm topic
            FirebaseMessaging.getInstance().subscribeToTopic("/topic/$token")
            FirebaseService.token = token
        })


    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        ZegoUIKitPrebuiltCallInvitationService.unInit()
    }

    private fun videoCallServices(userID: String) {
        val appID: Long = 67143961 // your App ID of Zoge Cloud
        val appSign = "93204944c2b3600a7dea583500c1a2f2c998e54e60bec98edaf7f8147ea3e00e" // your App Sign of Zoge Cloud
        val application = requireActivity().application // Android's application context
        val callInvitationConfig = ZegoUIKitPrebuiltCallInvitationConfig()
//        callInvitationConfig.notifyWhenAppRunningInBackgroundOrQuit = true
        val notificationConfig = ZegoNotificationConfig()
        notificationConfig.sound = "zego_uikit_sound_call"
        notificationConfig.channelID = "CallInvitation"
        notificationConfig.channelName = "CallInvitation"
        ZegoUIKitPrebuiltCallInvitationService.init(
            application,
            appID,
            appSign,
            userID,
            userID,
            callInvitationConfig
        )
    }
}