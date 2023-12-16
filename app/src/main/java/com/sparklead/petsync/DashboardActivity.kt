package com.sparklead.petsync

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.sparklead.petsync.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private var _binding: ActivityDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        binding.bottomNavigation.setupWithNavController(binding.fragmentContainerView.findNavController())
    }
}