package com.example.steptracker.ui

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.steptracker.R
import com.example.steptracker.viewmodel.DailyProgressViewModel
import com.github.mikephil.charting.charts.BarChart
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import com.github.mikephil.charting.data.BarEntry

import java.util.ArrayList
import com.github.mikephil.charting.data.BarData

import com.github.mikephil.charting.data.BarDataSet


class DailyProgressFragment : Fragment() {

    companion object {
        fun newInstance() = DailyProgressFragment()
    }

    private lateinit var viewModel: DailyProgressViewModel
    private lateinit var barChart: BarChart
    var barData: BarData? = null

    // variable for our bar data set.
    var barDataSet: BarDataSet? = null

    // array list for storing entries.

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.daily_progress_fragment, container, false)
        setUpViews(view)

        return view
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DailyProgressViewModel::class.java)
        viewModel.fetchCurrentSteps()
    }

    private fun getBarEntries(): ArrayList<Any>{
        // creating a new array list
        val barEntriesArrayList = ArrayList<Any>()

        // adding new entry to our array list with bar
        // entry and passing x and y axis value to it.
        barEntriesArrayList.add(BarEntry(1f, 4.0F))
        barEntriesArrayList.add(BarEntry(2f, 6.0F))
        barEntriesArrayList.add(BarEntry(3f, 8.0F))
        barEntriesArrayList.add(BarEntry(4f, 2.0F))
        barEntriesArrayList.add(BarEntry(5f, 4.0F))
        barEntriesArrayList.add(BarEntry(6f, 1.0F))

        return barEntriesArrayList
    }

    private fun setUpViews(view: View) {
        val circularIndicator=view.findViewById<CircularProgressBar>(R.id.progress_indicator)
        val circularIndicator1=view.findViewById<CircularProgressBar>(R.id.progress_indicator1)
        val circularIndicator2=view.findViewById<CircularProgressBar>(R.id.progress_indicator2)
        val circularIndicator3=view.findViewById<CircularProgressBar>(R.id.progress_indicator3)
        barChart=view.findViewById(R.id.bar_chart)
        val list=getBarEntries()
        val tvProgress=view.findViewById<TextView>(R.id.myTextProgress)
        barDataSet = BarDataSet(list as List<BarEntry>, "Daily Progress")

        // creating a new bar data and
        // passing our bar data set.
        barData = BarData(barDataSet)

        // below line is to set data
        // to our bar chart.

        // below line is to set data
        // to our bar chart.
        barChart.data = barData

        barChart.animateX(2000)
        barChart.animateY(2000)

        // adding color to our bar data set.
        val ll= listOf<Int>(Color.BLUE,Color.RED,Color.GREEN,Color.YELLOW)
        barDataSet?.colors = ll

        // setting text color.
        barDataSet?.setValueTextColor(Color.BLACK)

        barDataSet?.setValueTextSize(14f)
        barChart.description.isEnabled = false
        barChart.axisLeft.setDrawGridLines(false)
        barChart.axisRight.setDrawGridLines(false)
        barChart.getXAxis().isEnabled=false


        circularIndicator1.apply {
            backgroundProgressBarWidth=5.0F
            progressBarWidth=8.0F
            progressBarColor= Color.BLUE
            backgroundProgressBarColor=Color.GRAY
            roundBorder=true
            setProgressWithAnimation(70.0F,1000)
        }
        circularIndicator2.apply {
            backgroundProgressBarWidth=5.0F
            progressBarWidth=8.0F
            progressBarColor= Color.BLUE
            backgroundProgressBarColor=Color.GRAY
            roundBorder=true
            setProgressWithAnimation(40.0F,1000)
        }
        circularIndicator3.apply {
            backgroundProgressBarWidth=5.0F
            progressBarWidth=8.0F
            progressBarColor= Color.BLUE
            backgroundProgressBarColor=Color.GRAY
            roundBorder=true
            setProgressWithAnimation(20.0F,1000)
        }
        circularIndicator.apply {
            backgroundProgressBarWidth=10.0F
            progressBarWidth=15.0F
            progressBarColor= Color.BLUE
            backgroundProgressBarColor=Color.GRAY
            roundBorder=true
            setProgressWithAnimation(90.0F,1000)
        }

        tvProgress.text="90%"
        tvProgress.textSize=25.0F
    }

}