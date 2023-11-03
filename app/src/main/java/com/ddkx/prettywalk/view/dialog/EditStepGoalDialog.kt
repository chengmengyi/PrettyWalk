package com.ddkx.prettywalk.view.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddkx.prettywalk.R
import com.ddkx.prettywalk.adapter.StepGoalAdapter
import com.ddkx.prettywalk.databinding.DialogEditStepGoalBinding
import com.ddkx.prettywalk.ext.StepUtil

class EditStepGoalDialog:DialogFragment() {
    private val stepGoalAdapter by lazy { StepGoalAdapter(requireContext()) }
    private lateinit var binding:DialogEditStepGoalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.Dialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding=DialogEditStepGoalBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rv.apply {
            layoutManager=LinearLayoutManager(requireContext())
            adapter=stepGoalAdapter
        }

        binding.cancel.setOnClickListener { dismiss() }
        binding.sure.setOnClickListener {
            StepUtil.setStepGoalNum(stepGoalAdapter.getChooseStep())
            dismiss()
        }
    }
}