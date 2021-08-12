package com.example.steptracker.binding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.steptracker.ui.DailyProgressFragment
import com.example.steptracker.ui.MonthlyProgressFragment
import com.example.steptracker.ui.WeeklyProgressFragment


class FragmentAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {
    override fun getCount(): Int {
         return 3
    }

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = DailyProgressFragment()
            1 -> fragment = WeeklyProgressFragment()
            2 -> fragment = MonthlyProgressFragment()
        }
        return fragment!!
    }


    override fun getPageTitle(position: Int): CharSequence? {
        var fragment: String?=null
        when (position) {
            0 -> fragment = "Day"
            1 -> fragment = "Week"
            2 -> fragment = "Month"
        }
        return fragment!!
    }

}