package com.ddkx.prettywalk.fragment.info

import android.view.LayoutInflater
import com.ddkx.prettywalk.base.BaseFragment
import com.ddkx.prettywalk.databinding.FragmentInfoHeightWeightBinding

class InfoHeightWeightFragment:BaseFragment<FragmentInfoHeightWeightBinding>() {

    override fun initBinding(inflater: LayoutInflater): FragmentInfoHeightWeightBinding = FragmentInfoHeightWeightBinding.inflate(inflater)

    override fun initView() {

    }

    fun getHeight()=binding.editHeight.text.toString().trim()

    fun getWeight()=binding.editWeight.text.toString().trim()
}