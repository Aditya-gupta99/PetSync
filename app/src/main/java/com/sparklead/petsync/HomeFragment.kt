package com.sparklead.petsync

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.aar.tapholdupbutton.TapHoldUpButton
import com.sparklead.petsync.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var status = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnStart.setOnButtonClickListener(object : TapHoldUpButton.OnButtonClickListener{
            override fun onLongHoldStart(v: View?) {
                status = !status
                Toast.makeText(requireContext(),if(status) "Pet feeder started" else "Pet feeder off",Toast.LENGTH_LONG).show()
                if(status) binding.tvShowStatus.text = "Online" else binding.tvShowStatus.text = "Offline"
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


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}