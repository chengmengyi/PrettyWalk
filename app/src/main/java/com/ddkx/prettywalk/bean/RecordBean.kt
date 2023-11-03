package com.ddkx.prettywalk.bean

import androidx.annotation.Keep
import com.ddkx.prettywalk.ext.formatTime
import org.litepal.crud.LitePalSupport

@Keep
class RecordBean(
    var time:Long=System.currentTimeMillis(),
    var formatTime:String= formatTime(time),
    var stepNum:Int=0,
    var isMan:Boolean=true,
    var height:Int=170,
    var weight:Int=70
) : LitePalSupport(){
    override fun toString(): String {
        return "RecordBean(time=$time, formatTime='$formatTime', stepNum=$stepNum, isMan=$isMan, height=$height, weight=$weight)"
    }
}