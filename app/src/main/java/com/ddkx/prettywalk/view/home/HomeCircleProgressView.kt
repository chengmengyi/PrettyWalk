package com.ddkx.prettywalk.view.home

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.recyclerview.widget.GridLayoutManager
import com.ddkx.prettywalk.adapter.HomeInfoAdapter
import com.ddkx.prettywalk.base.BaseView
import com.ddkx.prettywalk.databinding.ViewHomeCircleProgressBinding
import com.ddkx.prettywalk.ext.RecordUtil
import com.ddkx.prettywalk.ext.StepUtil
import com.ddkx.prettywalk.ext.service.UpdateStepListener

class HomeCircleProgressView(context: Context,attributeSet: AttributeSet?):BaseView<ViewHomeCircleProgressBinding>(context,attributeSet),
    UpdateStepListener {
    private lateinit var homeInfoAdapter:HomeInfoAdapter

    override fun initBinding(): ViewHomeCircleProgressBinding = ViewHomeCircleProgressBinding.inflate(LayoutInflater.from(context))

    override fun setLayoutParams(): LayoutParams=LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)

    override fun viewCreated() {
        homeInfoAdapter=HomeInfoAdapter(context)
        binding.rvHome.apply {
            layoutManager=GridLayoutManager(context,3)
            adapter=homeInfoAdapter
        }
        updateInfo()
        RecordUtil.addUpdateListener(this)
        StepUtil.iSetGoalCall={
            updateInfo()
            updateStep(RecordUtil.todayRecordBean.stepNum)
        }
    }

    private fun updateInfo(){
        binding.tvGoal.text="Goal：${StepUtil.stepGoal}"
    }

    override fun updateStep(stepCount: Int) {
        binding.tvStep.text="$stepCount"
        homeInfoAdapter.updateInfo(stepCount)
        (stepCount * 100 / StepUtil.stepGoal).let {
            binding.circleView.setValue(
                (if (it>100) 100
                else if(it<0) 0
                else it).toFloat()
            )
        }
    }
}