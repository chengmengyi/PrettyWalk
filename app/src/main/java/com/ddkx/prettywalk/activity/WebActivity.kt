package com.ddkx.prettywalk.activity

import android.view.View
import com.ddkx.prettywalk.base.BaseActivity
import com.ddkx.prettywalk.databinding.ActivityWebBinding

class WebActivity:BaseActivity<ActivityWebBinding>() {
    override fun initBinding(): ActivityWebBinding = ActivityWebBinding.inflate(layoutInflater)

    override fun statusView(): View = binding.statusView

    override fun viewCreated() {
        binding.back.setOnClickListener { finish() }
        binding.web.apply {
            settings.javaScriptEnabled=true
            loadUrl("https://sites.google.com/view/prettywalk/home")
        }
    }
}