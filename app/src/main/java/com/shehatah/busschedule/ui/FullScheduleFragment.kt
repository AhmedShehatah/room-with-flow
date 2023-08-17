package com.shehatah.busschedule.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.shehatah.busschedule.App
import com.shehatah.busschedule.R
import com.shehatah.busschedule.databinding.FragmentFullScheduleBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FullScheduleFragment : Fragment(R.layout.fragment_full_schedule) {
    private lateinit var binding: FragmentFullScheduleBinding
    private lateinit var navController: NavController
    private val viewModel: BusScheduleViewModel by activityViewModels {
        BusScheduleViewModelFactory(
            (activity?.application as App).database.scheduleDao()
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFullScheduleBinding.bind(view)
        navController = Navigation.findNavController(view)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())


        val adapter = BusStopAdapter {
            val bundle = bundleOf("stopName" to it.stopName)
            navController.navigate(R.id.action_fullScheduleFragment_to_stopScheduleFragment, bundle)
        }
        binding.recyclerView.adapter = adapter
        lifecycleScope.launch(Dispatchers.IO) {

            viewModel.fullSchedule().collect()
            {
                adapter.submitList(it)
            }
        }
    }

}