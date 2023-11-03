package com.ddkx.prettywalk.activity

import android.view.View
import com.ddkx.prettywalk.base.BaseActivity
import com.ddkx.prettywalk.databinding.ActivityLaunchBinding

class LaunchActivity : BaseActivity<ActivityLaunchBinding>() {
    override fun initBinding(): ActivityLaunchBinding = ActivityLaunchBinding.inflate(layoutInflater)

    override fun statusView(): View = binding.statusView

    override fun viewCreated() {

    }

}