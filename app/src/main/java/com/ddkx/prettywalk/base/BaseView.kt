package com.ddkx.prettywalk.base

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.viewbinding.ViewBinding

abstract class BaseView<T : ViewBinding>(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    lateinit var binding:T

    init {
        binding=initBinding()
        val layoutParams = setLayoutParams()
        if (null==layoutParams){
            addView(binding.root)
        }else{
            addView(binding.root,layoutParams)
        }
        viewCreated()
    }

    abstract fun initBinding():T

    abstract fun viewCreated()

    // val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
    open fun setLayoutParams():LayoutParams?=null
}