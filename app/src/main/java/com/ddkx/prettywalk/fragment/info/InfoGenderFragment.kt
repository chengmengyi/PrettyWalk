package com.ddkx.prettywalk.fragment.info

import android.view.LayoutInflater
import com.ddkx.prettywalk.R
import com.ddkx.prettywalk.base.BaseFragment
import com.ddkx.prettywalk.databinding.FragmentInfoGenderBinding

class InfoGenderFragment:BaseFragment<FragmentInfoGenderBinding>() {
    private var man=true
    override fun initBinding(inflater: LayoutInflater): FragmentInfoGenderBinding = FragmentInfoGenderBinding.inflate(inflater)

    override fun initView() {
        binding.ivMan.setOnClickListener { chooseGender(true) }
        binding.ivWoman.setOnClickListener { chooseGender(false) }
    }

    fun getGenderIsMan()=man

    private fun chooseGender(man:Boolean){
        this.man=man
        if (man){
            binding.ivMan.setImageResource(R.drawable.man_sel)
            binding.ivWoman.setImageResource(R.drawable.woman_uns)
            binding.tvMan.setTextColor(resources.getColor(R.color.color_373739))
            binding.tvWoman.setTextColor(resources.getColor(R.color.color_9d9ba4))
        }else{
            binding.ivMan.setImageResource(R.drawable.man_uns)
            binding.ivWoman.setImageResource(R.drawable.woman_sel)
            binding.tvMan.setTextColor(resources.getColor(R.color.color_9d9ba4))
            binding.tvWoman.setTextColor(resources.getColor(R.color.color_373739))
        }
    }
}