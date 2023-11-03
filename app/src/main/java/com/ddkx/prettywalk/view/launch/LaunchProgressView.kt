package com.ddkx.prettywalk.view.launch

import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import com.ddkx.prettywalk.activity.HomeActivity
import com.ddkx.prettywalk.activity.InfoActivity
import com.ddkx.prettywalk.base.BaseView
import com.ddkx.prettywalk.databinding.ViewLaunchProgressBinding
import com.ddkx.prettywalk.ext.InfoUtil
import com.ddkx.prettywalk.ext.dp2px

class LaunchProgressView(context: Context, attrs: AttributeSet?):BaseView<ViewLaunchProgressBinding>(context,attrs) {
    private lateinit var animator: ValueAnimator

    override fun initBinding(): ViewLaunchProgressBinding = ViewLaunchProgressBinding.inflate(LayoutInflater.from(context))

    override fun setLayoutParams(): LayoutParams = LayoutParams(dp2px(240F), LayoutParams.WRAP_CONTENT)

    override fun viewCreated() {
        startAnimator()
    }

    private fun startAnimator(){
        animator=ValueAnimator.ofInt(1,100).apply {
            duration = 3000L
            interpolator = LinearInterpolator()
            addUpdateListener {
                val pro = it.animatedValue as Int
                binding.root.progress=pro
            }
            doOnEnd {
                (context as AppCompatActivity).apply {
                    startActivity(Intent(
                        this@apply,
                        if (InfoUtil.isFirst) InfoActivity::class.java
                        else HomeActivity::class.java
                    ))
                    finish()
                }
            }
            start()
        }
    }
}