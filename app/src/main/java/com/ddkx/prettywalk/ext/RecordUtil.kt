package com.ddkx.prettywalk.ext

import android.util.Log
import com.ddkx.prettywalk.bean.RecordBean
import com.ddkx.prettywalk.ext.service.UpdateStepListener
import org.litepal.LitePal

object RecordUtil {
    val recordList= mutableListOf<RecordBean>()
    var todayRecordBean=RecordBean()
    private val updateListener= arrayListOf<UpdateStepListener>()

    fun addUpdateListener(u:UpdateStepListener){
        updateListener.add(u)
    }

    fun queryRecordList(){
        updateListener.clear()
        val list = LitePal.select("*")
            .order("time desc")
            .find(RecordBean::class.java)
        list?.let {
            recordList.clear()
            recordList.addAll(list)
            recordList.forEach { Log.e("qwer","record:-->${it}") }
        }

        queryTodayRecord()
    }

    private fun queryTodayRecord(){
        val list = LitePal.select("*")
            .where("formatTime = ?", formatTime(System.currentTimeMillis()))
            .order("time desc")
            .find(RecordBean::class.java)
        if (list?.isNotEmpty()==true){
            todayRecordBean=list.first()
        }
    }

    fun saveRecord(step:Int){
        val todayTime = formatTime(System.currentTimeMillis())
        val exist = LitePal.isExist(RecordBean::class.java, "formatTime = ?",todayTime)
        todayRecordBean.stepNum=step
        if (exist){
            todayRecordBean.updateAll("formatTime = ?",todayTime)
        }else{
            todayRecordBean.save()
        }
        if (recordList.isNotEmpty()&& recordList.first().formatTime==todayTime){
            recordList[0]=todayRecordBean
        }else{
            recordList.add(0,todayRecordBean)
        }
        updateListener.forEach { it.updateStep(step) }
    }
}