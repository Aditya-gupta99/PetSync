package com.sparklead.petsync.ui.history

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sparklead.petsync.databinding.FragmentHistoryListBinding
import com.sparklead.petsync.dto.FeedDto
import com.sparklead.petsync.ui.adapter.PetFeederAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HistoryListFragment : Fragment() {

    private var _binding: FragmentHistoryListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HistoryViewModel
    private lateinit var petFeederAdapter : PetFeederAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[HistoryViewModel::class.java]
        setUpAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            while (isActive){
                viewModel.getFeedHistory()
                delay(10000)
            }
        }

        lifecycleScope.launch {
            viewModel.historyUiState.collect{
                when(it)  {
                    is HistoryUiState.Empty -> {

                    }

                    is HistoryUiState.Error -> {
                        Toast.makeText(requireContext(),it.message,Toast.LENGTH_LONG).show()
                    }

                    is HistoryUiState.Loading -> {}

                    is HistoryUiState.Success -> {
                        success(it.list)
                    }
                }
            }
        }

    }

    private fun setUpAdapter() {
        petFeederAdapter = PetFeederAdapter()
        binding.rvCryptoList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = petFeederAdapter
        }
    }

    private fun success(list : List<FeedDto>) {
        petFeederAdapter.differ.submitList(list.sortedByDescending { it.timestamp })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}