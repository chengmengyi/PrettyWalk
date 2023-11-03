package com.ddkx.prettywalk.view.info

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.ddkx.prettywalk.R
import com.ddkx.prettywalk.base.BaseView
import com.ddkx.prettywalk.databinding.ViewInfoTabBinding

class InfoTabView(context: Context,attributeSet: AttributeSet?):BaseView<ViewInfoTabBinding>(context,attributeSet) {
    override fun initBinding(): ViewInfoTabBinding = ViewInfoTabBinding.inflate(LayoutInflater.from(context))

    override fun viewCreated() {

    }

    fun update(position:Int){
        if (position==0){
            binding.tab1.setBackgroundResource(R.drawable.bg_1ec9ff_ff3fd5_8dp)
            binding.tab2.setBackgroundResource(R.drawable.bg_1ec9ff_ff3fd5_8dp_20al)
        }else{
            binding.tab1.setBackgroundResource(R.drawable.bg_1ec9ff_ff3fd5_8dp_20al)
            binding.tab2.setBackgroundResource(R.drawable.bg_1ec9ff_ff3fd5_8dp)
        }
    }
}