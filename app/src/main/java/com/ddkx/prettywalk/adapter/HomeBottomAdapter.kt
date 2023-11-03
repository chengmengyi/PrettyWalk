package com.ddkx.prettywalk.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ddkx.prettywalk.R
import com.ddkx.prettywalk.databinding.ItemHomeBottomBinding

class HomeBottomAdapter(
    private val context:Context,
    private val click:(index:Int)->Unit
):Adapter<HomeBottomAdapter.BottomView>() {
    private var chooseIndex=0

    inner class BottomView(view:ItemHomeBottomBinding):ViewHolder(view.root){
        val icon=view.icon
        init {
            view.root.setOnClickListener {
                chooseIndex=layoutPosition
                notifyDataSetChanged()
                click.invoke(chooseIndex)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottomView {
        return BottomView(ItemHomeBottomBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun getItemCount(): Int = 3

    override fun onBindViewHolder(holder: BottomView, position: Int) {
        holder.icon.setImageResource(
            when(position){
                0-> R.drawable.home_select
                1-> R.drawable.record_select
                2-> R.drawable.mine_select
                else-> R.drawable.home_select
            }
        )
        holder.icon.isSelected=chooseIndex==position
    }
}