package com.ddkx.prettywalk.view.home

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ddkx.prettywalk.R
import com.ddkx.prettywalk.base.BaseView
import com.ddkx.prettywalk.databinding.ViewHomeFrameBinding
import com.ddkx.prettywalk.fragment.home.HomeFragment
import com.ddkx.prettywalk.fragment.home.MineFragment
import com.ddkx.prettywalk.fragment.home.RecordFragment

class HomeFrameView(context: Context,attributeSet: AttributeSet?):BaseView<ViewHomeFrameBinding>(context,attributeSet) {
    private var showIndex=-1
    private val fragmentMap= hashMapOf<Int,Fragment>()


    override fun initBinding(): ViewHomeFrameBinding = ViewHomeFrameBinding.inflate(LayoutInflater.from(context))

    override fun setLayoutParams(): LayoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)

    override fun viewCreated() {

    }

    fun showFragment(index:Int){
        if (index==showIndex){
            return
        }
        if (showIndex>=0){
            fragmentMap[showIndex]?.let {
                val fragmentTransaction = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
                fragmentTransaction.hide(it)
                fragmentTransaction.commit()
            }
        }
        if (null==fragmentMap[index]){
            fragmentMap[index]=when(index){
                0->HomeFragment()
                1->RecordFragment()
                2->MineFragment()
                else->HomeFragment()
            }
            (context as AppCompatActivity).supportFragmentManager.beginTransaction().add(R.id.root,fragmentMap[index]!!).commit()
        }else{
            (context as AppCompatActivity).supportFragmentManager.beginTransaction().show(fragmentMap[index]!!).commit()
        }
        showIndex=index
    }
}