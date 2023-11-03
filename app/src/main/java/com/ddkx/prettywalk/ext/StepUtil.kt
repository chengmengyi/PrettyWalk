package com.ddkx.prettywalk.ext

import com.ddkx.prettywalk.bean.RecordBean
import com.tencent.mmkv.MMKV

object StepUtil {
    var stepGoal=8000
    var iSetGoalCall: (() -> Unit?)? =null

    fun setStepGoalNum(step:Int){
        stepGoal=step
        MMKV.defaultMMKV().encode("stepGoal",stepGoal)
        iSetGoalCall?.invoke()
    }

    fun getLocalStepGoal(){
        stepGoal=MMKV.defaultMMKV().decodeInt("stepGoal",8000)
    }

    fun getStepMiles(recordBean: RecordBean):Double{
        val length = if (recordBean.isMan){
            recordBean.height*(if (recordBean.height<160){ 0.415 } else if (recordBean.height in 160..170){ 0.445 } else { 0.475 })
        }else{
            recordBean.height*(if (recordBean.height<150){ 0.413 } else if (recordBean.height in 150..160){ 0.43 } else { 0.453 })
        }
        return length*recordBean.stepNum/100
    }

    fun getCalories(recordBean: RecordBean):Double{
        return getStepMiles(recordBean)*100*recordBean.height*recordBean.weight * 0.0000191*0.01
    }
}