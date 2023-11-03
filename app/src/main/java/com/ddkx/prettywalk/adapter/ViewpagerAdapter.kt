package com.ddkx.prettywalk.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewpagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, private val list:ArrayList<Fragment>) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment = list[position]
}