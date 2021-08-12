package com.example.steptracker.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.steptracker.service.FitnessService
import com.google.android.gms.fitness.Fitness

object DailyProgressRepository {


    fun getSteps(context:Context){

        FitnessService.fetchDataFromServer(context)


    }


}