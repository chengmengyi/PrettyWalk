package com.ddkx.prettywalk.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ddkx.prettywalk.R
import com.ddkx.prettywalk.databinding.ItemStepGoalBinding
import com.ddkx.prettywalk.ext.StepUtil

class StepGoalAdapter(
    private val context: Context
):Adapter<StepGoalAdapter.GoalView>() {
    private var chooseStep=StepUtil.stepGoal

    private val list= arrayOf(6000,8000,10000,15000,20000)

    fun getChooseStep()=chooseStep

    inner class GoalView(view:ItemStepGoalBinding):ViewHolder(view.root){
        val root=view
        init {
            view.root.setOnClickListener {
                chooseStep=list[layoutPosition]
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalView {
        return GoalView(ItemStepGoalBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: GoalView, position: Int) {
        holder.root.step.text="${list[position]}"
        val choose = chooseStep == list[position]
        holder.root.step.setTextColor(if (choose) context.resources.getColor(R.color.color_373739) else context.resources.getColor(R.color.color_9d9ba4))
        holder.root.choose.setImageResource(if (choose) R.drawable.sel else R.drawable.uns)
    }

}