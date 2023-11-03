package com.ddkx.prettywalk.activity

import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.ddkx.prettywalk.adapter.ViewpagerAdapter
import com.ddkx.prettywalk.base.BaseActivity
import com.ddkx.prettywalk.databinding.ActivityInfoBinding
import com.ddkx.prettywalk.ext.InfoUtil
import com.ddkx.prettywalk.ext.showToast
import com.ddkx.prettywalk.fragment.info.InfoGenderFragment
import com.ddkx.prettywalk.fragment.info.InfoHeightWeightFragment

class InfoActivity:BaseActivity<ActivityInfoBinding>() {
    private val fragmentList= arrayListOf<Fragment>()

    override fun initBinding(): ActivityInfoBinding = ActivityInfoBinding.inflate(layoutInflater)

    override fun statusView(): View = binding.statusView

    override fun viewCreated() {
        setAdapter()
        binding.tvNext.setOnClickListener {
            if (binding.viewPager.currentItem==fragmentList.size-1){
                checkInfo()
                return@setOnClickListener
            }
            binding.viewPager.currentItem++
        }
        binding.tvSkip.setOnClickListener { toHome() }
    }

    private fun checkInfo(){
        val isMan = (fragmentList.first() as InfoGenderFragment).getGenderIsMan()
        val infoHeightWeightFragment = fragmentList.last() as InfoHeightWeightFragment
        val height = infoHeightWeightFragment.getHeight()
        val weight = infoHeightWeightFragment.getWeight()
        if (height.isEmpty()){
            showToast("Enter your height")
            return
        }
        if (weight.isEmpty()){
            showToast("Enter your weight")
            return
        }
        InfoUtil.saveInfo(isMan,height, weight)
        toHome()
    }

    private fun setAdapter(){
        fragmentList.clear()
        fragmentList.add(InfoGenderFragment())
        fragmentList.add(InfoHeightWeightFragment())
        binding.viewPager.apply {
            isUserInputEnabled=false
            adapter=ViewpagerAdapter(supportFragmentManager,lifecycle,fragmentList)
            registerOnPageChangeCallback(object : OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.infoTabView.update(position)
                }
            })
        }
    }

    private fun toHome(){
        startActivity(Intent(this,HomeActivity::class.java))
        finish()
    }

    override fun onBackPressed() {
        if (binding.viewPager.currentItem==fragmentList.size-1){
            binding.viewPager.currentItem--
            return
        }
        finish()
    }
}