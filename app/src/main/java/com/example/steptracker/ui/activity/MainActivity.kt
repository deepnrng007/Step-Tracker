package com.example.steptracker.ui.activity

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.steptracker.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataSet
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.data.Field
import com.google.android.gms.fitness.request.OnDataPointListener
import com.google.android.gms.fitness.request.SensorRequest
import com.google.android.gms.tasks.OnSuccessListener
import com.google.gson.FieldNamingStrategy
import java.security.Permission
import java.util.concurrent.TimeUnit
import java.util.jar.Manifest


class MainActivity : AppCompatActivity() {


    private var fitnessOptions: FitnessOptions? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkPermission()
    }

    fun checkPermission() {

        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACTIVITY_RECOGNITION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACTIVITY_RECOGNITION
                ), 1001
            )
        }
        else{
            checkGoogleFitPermissions()
        }
    }

    private fun checkGoogleFitPermissions() {

        fitnessOptions = FitnessOptions.builder()
            .addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.TYPE_CALORIES_EXPENDED, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.AGGREGATE_CALORIES_EXPENDED, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.TYPE_DISTANCE_DELTA, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.AGGREGATE_DISTANCE_DELTA, FitnessOptions.ACCESS_READ)
            .build()

        val account = getGoogleAccount()

        if (!GoogleSignIn.hasPermissions(account, fitnessOptions)) {
            GoogleSignIn.requestPermissions(this, 1, account, fitnessOptions)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==1){

//            getTodayData()

            subscribeAndGetRealTimeData(DataType.TYPE_STEP_COUNT_DELTA)

        }
    }

    private fun getGoogleAccount(): GoogleSignInAccount {
        return GoogleSignIn.getAccountForExtension(this, fitnessOptions)
    }


    fun subscribeAndGetRealTimeData(dataType: DataType) {
        Fitness.getSensorsClient(this, getGoogleAccount())
            .add(
                SensorRequest.Builder().setDataType(dataType).setSamplingRate(1, TimeUnit.SECONDS)
                    .build()
            ) {
                val value = it.getValue(Field.FIELD_STEPS)
                Log.i("MAIN", value.toString())
            }.addOnFailureListener{
                Log.i("MAIN", it.toString())
            }
    }


    fun getTodayData() {
        Fitness.getHistoryClient(this, getGoogleAccount())
            .readDailyTotal(DataType.TYPE_STEP_COUNT_DELTA).addOnSuccessListener {
                Log.i("MAin", "SUCCESS")
            }.addOnFailureListener {
                Log.i("MAin", "FAILURE")
            }
        Fitness.getHistoryClient(this, getGoogleAccount())
            .readDailyTotal(DataType.TYPE_CALORIES_EXPENDED).addOnSuccessListener {
                Log.i("MAin", "SUCCESS")
            }.addOnFailureListener {
                Log.i("MAin", "FAILURE")
            }
        Fitness.getHistoryClient(this, getGoogleAccount())
            .readDailyTotal(DataType.TYPE_DISTANCE_DELTA).addOnSuccessListener {
                Log.i("MAin", "SUCCESS")
            }.addOnFailureListener {
                Log.i("MAin", "FAILURE")
            }
    }

    fun requestForHistory() {

    }
}