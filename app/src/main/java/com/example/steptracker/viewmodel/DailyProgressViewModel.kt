package com.example.steptracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.steptracker.repository.DailyProgressRepository

class DailyProgressViewModel(application: Application) : AndroidViewModel(application) {

    private val context=application.applicationContext

    fun fetchCurrentSteps(){

        DailyProgressRepository.getSteps(context)

    }

}