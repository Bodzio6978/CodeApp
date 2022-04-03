package com.gmail.bodziowaty6978.kodyzabka.feature_code.presentation.codes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.gmail.bodziowaty6978.kodyzabka.R
import com.gmail.bodziowaty6978.kodyzabka.databinding.ActivityCodeBinding
import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.model.Code
import com.gmail.bodziowaty6978.kodyzabka.feature_code.presentation.util.SliderAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CodeActivity : AppCompatActivity() {

    private val viewModel: CodesViewModel by viewModels()
    private lateinit var binding: ActivityCodeBinding
    private val codeItems = mutableListOf<Code>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code)

        binding = ActivityCodeBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        setSupportActionBar(binding.tbCode)

        lifecycleScope.launch {
            binding.btScanned.setOnClickListener {
                binding.vp2Code.apply {
                    currentItem += 1
                }

            }
        }

        val adapter = SliderAdapter(codeItems)
        binding.vp2Code.adapter = adapter

        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { codeState ->
                codeState.codes.forEach { code ->
                    if (!codeItems.contains(code)) {
                        codeItems.add(code)
                        binding.vp2Code.adapter?.apply {
                            notifyItemInserted(codeItems.size - 1)
                        }
                    }
                }
                codeItems.forEachIndexed { index, code ->
                    if (!codeState.codes.contains(code)) {
                        codeItems.remove(code)
                        binding.vp2Code.adapter?.apply {
                            notifyItemRemoved(index)
                        }
                    }
                }
            }
        }


    }


}