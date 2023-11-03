package com.ddkx.prettywalk.ext

import com.tencent.mmkv.MMKV

object InfoUtil {
    var isMan=true
    var height=170
    var weight=70
    var isFirst=true

    fun getLocalInfo(){
        isFirst=MMKV.defaultMMKV().decodeBool("isFirst",true)
        isMan=MMKV.defaultMMKV().decodeBool("isMan",true)
        height=MMKV.defaultMMKV().decodeInt("height",170)
        weight=MMKV.defaultMMKV().decodeInt("weight",70)
    }

    fun saveInfo(isMan:Boolean,height:String,weight:String){
        this.isMan=isMan
        MMKV.defaultMMKV().encode("isMan",isMan)
        runCatching {
            this.height=height.toInt()
            MMKV.defaultMMKV().encode("height",height.toInt())
        }
        runCatching {
            this.weight=weight.toInt()
            MMKV.defaultMMKV().encode("weight",weight.toInt())
        }
        isFirst=false
        MMKV.defaultMMKV().encode("isFirst",isFirst)
        RecordUtil.todayRecordBean.height=InfoUtil.height
        RecordUtil.todayRecordBean.weight=InfoUtil.weight
        RecordUtil.todayRecordBean.isMan=InfoUtil.isMan
    }
}