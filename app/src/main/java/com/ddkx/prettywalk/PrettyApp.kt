package com.ddkx.prettywalk

import android.app.Application
import com.ddkx.prettywalk.ext.InfoUtil
import com.ddkx.prettywalk.ext.RecordUtil
import com.ddkx.prettywalk.ext.StepUtil
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import com.tencent.mmkv.MMKV
import org.litepal.LitePal

lateinit var prettyApp: PrettyApp
class PrettyApp :Application() {
    override fun onCreate() {
        super.onCreate()
        prettyApp=this
        Firebase.initialize(this)
        MMKV.initialize(this)
        LitePal.initialize(this)
        StepUtil.getLocalStepGoal()
        InfoUtil.getLocalInfo()
        RecordUtil.queryRecordList()
    }
}