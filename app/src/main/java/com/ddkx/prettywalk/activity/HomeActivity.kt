package com.ddkx.prettywalk.activity

import android.Manifest
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import com.ddkx.prettywalk.base.BaseActivity
import com.ddkx.prettywalk.databinding.ActivityHomeBinding
import com.ddkx.prettywalk.ext.RecordUtil
import com.ddkx.prettywalk.ext.service.StepService
import com.ddkx.prettywalk.ext.service.UpdateStepListener
import com.ddkx.prettywalk.ext.showToast

class HomeActivity:BaseActivity<ActivityHomeBinding>() {
    private var requestActivity=true
    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){
        if (it){
            requestPostNotify()
        }else{
            showToast("request permission fail")
        }
    }

    override fun initBinding(): ActivityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)

    override fun statusView(): View = binding.statusView

    override fun viewCreated() {
        clickBottom(0)
        binding.homeBottomView.clickBottomCall={ clickBottom(it) }
        requestPermissionLauncher.launch(Manifest.permission.ACTIVITY_RECOGNITION)
    }

    private fun clickBottom(index:Int){
        binding.homeFrameView.showFragment(index)
    }

    private fun requestPostNotify(){
        if (requestActivity){
            requestActivity=false
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU){
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }else{
                startService()
            }
        }else{
            startService()
        }
    }

    private fun startService(){
        val intent = Intent(this, StepService::class.java)
        startService(intent)
    }
}