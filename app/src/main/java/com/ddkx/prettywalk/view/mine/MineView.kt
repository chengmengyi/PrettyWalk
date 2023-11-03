package com.ddkx.prettywalk.view.mine

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddkx.prettywalk.adapter.MineAdapter
import com.ddkx.prettywalk.base.BaseView
import com.ddkx.prettywalk.databinding.ViewMineBinding

class MineView(context: Context,attributeSet: AttributeSet?):BaseView<ViewMineBinding>(context,attributeSet) {

    override fun initBinding(): ViewMineBinding = ViewMineBinding.inflate(LayoutInflater.from(context))

    override fun setLayoutParams(): LayoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)

    override fun viewCreated() {
        binding.root.apply {
            layoutManager=LinearLayoutManager(context)
            adapter=MineAdapter(context)
        }
    }
}