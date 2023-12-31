package com.sparklead.petsync.home

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.aar.tapholdupbutton.TapHoldUpButton
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.sparklead.petsync.databinding.FragmentHomeBinding
import com.sparklead.petsync.firebase.FirebaseService
import com.sparklead.petsync.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.homeUiState.collect{
                when(it) {
                    is HomeUiState.Empty -> {}

                    is HomeUiState.Error -> {
                        Toast.makeText(requireContext(),it.message,Toast.LENGTH_LONG).show()
                    }

                    is HomeUiState.Loading -> {}

                    is HomeUiState.OnOffSuccess -> {
                        Toast.makeText(requireContext(),it.onOffSuccess.toString(),Toast.LENGTH_LONG).show()
                    }
                }
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


    private fun fcmSetup() {

        FirebaseService.sharedPref = requireContext().getSharedPreferences(Constants.SHARED_PREFERENCE, Context.MODE_PRIVATE)
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("Fcm token", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            token = task.result
            Log.e("token",token)
            // subscribing fcm topic
            FirebaseMessaging.getInstance().subscribeToTopic("/topic/$token")
            FirebaseService.token = token
        })


    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}