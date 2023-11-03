package com.ddkx.prettywalk.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ddkx.prettywalk.bean.RecordBean
import com.ddkx.prettywalk.databinding.ItemHomeInfoBinding
import com.ddkx.prettywalk.ext.InfoUtil
import com.ddkx.prettywalk.ext.RecordUtil
import com.ddkx.prettywalk.ext.StepUtil
import com.ddkx.prettywalk.ext.twoPoint

class HomeInfoAdapter(private val context: Context):Adapter<HomeInfoAdapter.InfoView>() {

    inner class InfoView(view:ItemHomeInfoBinding):ViewHolder(view.root){
        val view=view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoView {
        return InfoView(ItemHomeInfoBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun getItemCount(): Int = 3

    override fun onBindViewHolder(holder: InfoView, position: Int) {
        when (position) {
            0 -> {
                holder.view.tvNum.text="${RecordUtil.todayRecordBean.stepNum}"
                holder.view.tvType.text="Today’s Step"
            }
            1 -> {
                holder.view.tvNum.text="${twoPoint(StepUtil.getStepMiles(RecordUtil.todayRecordBean))}m"
                holder.view.tvType.text="Step Miles"
            }
            2 -> {
                holder.view.tvNum.text="${twoPoint(StepUtil.getCalories(RecordUtil.todayRecordBean))}"
                holder.view.tvType.text="Calories"
            }
            else->{
                holder.view.tvNum.text=""
                holder.view.tvType.text=""
            }
        }
    }

    fun updateInfo(step:Int,notify:Boolean=true){
        RecordUtil.todayRecordBean.stepNum=step
        if (notify){
            notifyDataSetChanged()
        }
    }
}