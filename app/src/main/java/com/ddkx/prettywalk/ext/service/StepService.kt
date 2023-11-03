package com.ddkx.prettywalk.ext.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.ddkx.prettywalk.R
import com.ddkx.prettywalk.ext.RecordUtil

class StepService : Service(), SensorEventListener {
    private val notificationId = "step_service_id"
    private val notificationName = "step_service_name"
    private var sensorManager: SensorManager? = null
    var stepCount = 0
        private set
    private var hasRecord = false
    private var hasStepCount = 0
    private var previousStepCount = 0
    private val stepBinder = StepBinder()

    @RequiresApi(api = Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        initNotification()
        stepCount=RecordUtil.todayRecordBean.stepNum
        Thread { startStepDetector() }.start()
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private fun initNotification() {
        val builder = NotificationCompat.Builder(this, notificationId)
            .setWhen(System.currentTimeMillis())
            .setContentTitle(resources.getString(R.string.app_name))
            .setCategory(NotificationCompat.CATEGORY_SERVICE)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setChannelId(notificationId)
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(
            notificationId,
            notificationName,
            NotificationManager.IMPORTANCE_HIGH
        )
        channel.enableLights(true)
        channel.setShowBadge(true)
        manager.createNotificationChannel(channel)
        builder.setContentTitle(resources.getString(R.string.app_name))
        builder.setContentText("${resources.getString(R.string.app_name)} Running...")
        builder.setSmallIcon(R.drawable.logo)
        builder.setOngoing(true)
        builder.setOnlyAlertOnce(true)
        startForeground(100, builder.build())
    }

    private fun updateNotification() {
        RecordUtil.saveRecord(stepCount)
//        updateStepListener?.updateStep(stepCount)
    }

    private var updateStepListener: UpdateStepListener? = null
    fun setUpdateStepListener(updateStepListener: UpdateStepListener?) {
        this.updateStepListener = updateStepListener
    }

    override fun onBind(intent: Intent): IBinder? {
        return stepBinder
    }

    inner class StepBinder : Binder() {
        val service: StepService
            get() = this@StepService
    }

    override fun onStart(intent: Intent, startId: Int) {
        super.onStart(intent, startId)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    private fun startStepDetector() {
        if (sensorManager != null) {
            sensorManager = null
        }
        sensorManager = this.getSystemService(SENSOR_SERVICE) as SensorManager
        addCountStepListener()
    }

    private fun addCountStepListener() {
        val countSensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        val detectorSensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)
        if (countSensor != null) {
            stepSensorType = Sensor.TYPE_STEP_COUNTER
            sensorManager!!.registerListener(
                this@StepService,
                countSensor,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        } else if (detectorSensor != null) {
            stepSensorType = Sensor.TYPE_STEP_DETECTOR
            sensorManager!!.registerListener(
                this@StepService,
                detectorSensor,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (stepSensorType == Sensor.TYPE_STEP_COUNTER) {
            //获取当前传感器返回的临时步数
            val tempStep = event.values[0].toInt()
            //首次如果没有获取手机系统中已有的步数则获取一次系统中APP还未开始记步的步数
            if (!hasRecord) {
                hasRecord = true
                hasStepCount = tempStep
            } else {
                //获取APP打开到现在的总步数=本次系统回调的总步数-APP打开之前已有的步数
                val thisStepCount = tempStep - hasStepCount
                //本次有效步数=（APP打开后所记录的总步数-上一次APP打开后所记录的总步数）
                val thisStep = thisStepCount - previousStepCount
                //总步数=现有的步数+本次有效步数
                stepCount += thisStep
                //记录最后一次APP打开到现在的总步数
                previousStepCount = thisStepCount
            }
        } else if (stepSensorType == Sensor.TYPE_STEP_DETECTOR) {
            if (event.values[0].toDouble() == 1.0) {
                stepCount++
            }
        }
        updateNotification()
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
    override fun onDestroy() {
        super.onDestroy()
        stopForeground(true)
    }

    override fun onUnbind(intent: Intent): Boolean {
        return super.onUnbind(intent)
    }

    companion object {
        private var stepSensorType = -1
    }
}