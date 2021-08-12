package com.example.steptracker.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.steptracker.R
import com.example.steptracker.viewmodel.WeeklyProgressViewModel

class WeeklyProgressFragment : Fragment() {

    companion object {
        fun newInstance() = WeeklyProgressFragment()
    }

    private lateinit var viewModel: WeeklyProgressViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.weekly_progress_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WeeklyProgressViewModel::class.java)
        // TODO: Use the ViewModel
    }

}