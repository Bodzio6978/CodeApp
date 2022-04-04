package com.gmail.bodziowaty6978.kodyzabka.feature_code.presentation.add_edit_code

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.gmail.bodziowaty6978.kodyzabka.R
import com.gmail.bodziowaty6978.kodyzabka.databinding.FragmentAddEditBinding
import com.gmail.bodziowaty6978.kodyzabka.databinding.FragmentScannerBinding
import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.model.Code
import kotlinx.coroutines.launch
import android.view.MotionEvent

import android.view.View.OnTouchListener
import androidx.navigation.Navigation
import androidx.navigation.findNavController


class AddEditFragment : Fragment() {

    private var _binding: FragmentAddEditBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddEditViewModel by viewModels()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddEditBinding.inflate(inflater,container,false)

        lifecycleScope.launch {
            binding.fabSave.setOnClickListener {
                viewModel.saveCode(
                    Code(
                        binding.etCode.text.toString().trim(),
                        System.currentTimeMillis(),
                        binding.etCodeOwner.text.toString().trim()
                    )
                )
            }
        }

        lifecycleScope.launch {
            binding.etCode.apply {
                setOnTouchListener { _, motionEvent ->
                    if (motionEvent.action == MotionEvent.ACTION_UP) {
                        if (motionEvent.rawX >= this.right - this.compoundDrawables[2].bounds.width()
                        ) {
                            //TODO NAVIGATION
                        }
                    }
                    false
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}