package com.ddkx.prettywalk.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ddkx.prettywalk.R
import com.ddkx.prettywalk.activity.WebActivity
import com.ddkx.prettywalk.databinding.ItemMineBinding
import com.ddkx.prettywalk.view.dialog.EditStepGoalDialog

class MineAdapter(
    private val context: Context,
):Adapter<MineAdapter.MineView>() {
    inner class MineView(view:ItemMineBinding):ViewHolder(view.root){
        val view=view
        init {
            view.root.setOnClickListener { clickItem(layoutPosition) }
        }
    }

    private fun clickItem(index:Int){
        when(index){
            0->context.startActivity(Intent(context,WebActivity::class.java))
            1->{
                runCatching {
                    val intent = Intent(Intent.ACTION_SENDTO)
                    intent.data= Uri.parse("mailto:")
                    intent.putExtra(Intent.EXTRA_EMAIL,"jarrdoee@gmail.com")
                    context.startActivity(intent)
                }
            }
            2->{
                (context as AppCompatActivity).let {
                    EditStepGoalDialog().show(it.supportFragmentManager,"EditStepGoalDialog")
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MineView {
        return MineView(ItemMineBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun getItemCount(): Int = 3

    override fun onBindViewHolder(holder: MineView, position: Int) {
        holder.view.icon.setImageResource(
            when(position){
                0-> R.drawable.privavy
                1->R.drawable.contact
                2->R.drawable.set
                else->R.drawable.set
            }
        )

        holder.view.name.text= when(position){
            0->"Privacy Policy"
            1->"Contact Us"
            2->"Set Profiles"
            else->""
        }
    }
}