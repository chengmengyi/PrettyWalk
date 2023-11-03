package com.ddkx.prettywalk.view.record

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddkx.prettywalk.adapter.RecordAdapter
import com.ddkx.prettywalk.base.BaseView
import com.ddkx.prettywalk.databinding.ViewRecordListBinding
import com.ddkx.prettywalk.ext.RecordUtil
import com.ddkx.prettywalk.ext.service.UpdateStepListener

class RecordListView(context: Context,attributeSet: AttributeSet?):BaseView<ViewRecordListBinding>(context,attributeSet),
    UpdateStepListener {
    private lateinit var recordAdapter: RecordAdapter

    override fun initBinding(): ViewRecordListBinding = ViewRecordListBinding.inflate(LayoutInflater.from(context))

    override fun setLayoutParams(): LayoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)

    override fun viewCreated() {
        recordAdapter=RecordAdapter(context)
        binding.root.apply {
            layoutManager=LinearLayoutManager(context)
            adapter=recordAdapter
        }
        RecordUtil.addUpdateListener(this)
    }

    override fun updateStep(stepCount: Int) {
        recordAdapter.notifyDataSetChanged()
    }
}