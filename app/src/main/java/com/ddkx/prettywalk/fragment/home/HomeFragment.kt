package com.ddkx.prettywalk.fragment.home

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.ddkx.prettywalk.base.BaseFragment
import com.ddkx.prettywalk.databinding.FragmentHomeBinding
import com.ddkx.prettywalk.ext.RecordUtil
import com.ddkx.prettywalk.view.dialog.EditStepGoalDialog

class HomeFragment:BaseFragment<FragmentHomeBinding>() {
    override fun initBinding(inflater: LayoutInflater): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater)

    override fun initView() {
        binding.edit.setOnClickListener {
            (context as AppCompatActivity).let {
                EditStepGoalDialog().show(it.supportFragmentManager,"EditStepGoalDialog")
            }
        }
    }
}