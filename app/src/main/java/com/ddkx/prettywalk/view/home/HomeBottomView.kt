package com.ddkx.prettywalk.view.home

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddkx.prettywalk.adapter.HomeBottomAdapter
import com.ddkx.prettywalk.base.BaseView
import com.ddkx.prettywalk.databinding.ViewHomeBottomBinding
import com.ddkx.prettywalk.ext.dp2px

class HomeBottomView(context: Context,attributeSet: AttributeSet?):BaseView<ViewHomeBottomBinding>(context,attributeSet) {
    var clickBottomCall: ((index:Int) -> Unit?)? =null

    override fun initBinding(): ViewHomeBottomBinding = ViewHomeBottomBinding.inflate(LayoutInflater.from(context))

    override fun setLayoutParams(): LayoutParams =  LayoutParams(LayoutParams.MATCH_PARENT, dp2px(65F))

    override fun viewCreated() {
        binding.root.apply {
            layoutManager=GridLayoutManager(context,3)
            adapter=HomeBottomAdapter(context){
                clickBottomCall?.invoke(it)
            }
        }
    }
}