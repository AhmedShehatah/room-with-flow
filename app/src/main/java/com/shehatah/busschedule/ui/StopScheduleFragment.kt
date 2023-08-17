package com.shehatah.busschedule.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.shehatah.busschedule.App
import com.shehatah.busschedule.R
import com.shehatah.busschedule.databinding.FragmentStopScheduleBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StopScheduleFragment : Fragment(R.layout.fragment_stop_schedule) {

    private lateinit var binding: FragmentStopScheduleBinding
    private val viewModel: BusScheduleViewModel by activityViewModels {
        BusScheduleViewModelFactory(
            (activity?.application as App).database.scheduleDao()
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStopScheduleBinding.bind(view)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val busAdapter = BusStopAdapter() {

        }
        binding.recyclerView.adapter = busAdapter


        lifecycleScope.launch(Dispatchers.IO) {
            arguments?.getString("stopName")?.let {
                viewModel.scheduleForStopName(
                    it
                ).collect() { list ->
                    busAdapter.submitList(list)
                }
            }


        }

    }

}