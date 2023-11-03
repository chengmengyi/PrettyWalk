package com.ddkx.prettywalk.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.ddkx.prettywalk.R
import com.gyf.immersionbar.ImmersionBar

abstract class BaseActivity<T:ViewBinding>: AppCompatActivity() {
    lateinit var binding:T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=initBinding()
        setContentView(binding.root)
        initImmersionBar()
        viewCreated()
    }

    abstract fun initBinding():T

    abstract fun statusView(): View

    abstract fun viewCreated()

    private fun initImmersionBar(){
        ImmersionBar.with(this).apply {
            navigationBarColor(R.color.white)
            autoDarkModeEnable(true)
            statusBarDarkFont(true)
            statusBarView(statusView())
            init()
        }
    }
}