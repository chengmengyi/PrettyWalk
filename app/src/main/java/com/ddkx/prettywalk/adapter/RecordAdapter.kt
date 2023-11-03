package com.ddkx.prettywalk.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ddkx.prettywalk.databinding.ItemRecordBinding
import com.ddkx.prettywalk.ext.RecordUtil
import com.ddkx.prettywalk.ext.StepUtil
import com.ddkx.prettywalk.ext.twoPoint

class RecordAdapter(private val context: Context):Adapter<RecordAdapter.RecordView>() {

    inner class RecordView(view:ItemRecordBinding):ViewHolder(view.root){
        val view=view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordView {
        return RecordView(ItemRecordBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun getItemCount(): Int = RecordUtil.recordList.size

    override fun onBindViewHolder(holder: RecordView, position: Int) {
        val recordBean = RecordUtil.recordList[position]
        holder.view.tvTime.text=recordBean.formatTime
        holder.view.tvStep.text="${recordBean.stepNum}"
        holder.view.tvMiles.text="${twoPoint(StepUtil.getStepMiles(recordBean))}m"
        holder.view.tvCalories.text="${twoPoint(StepUtil.getCalories(recordBean))}"
    }
}