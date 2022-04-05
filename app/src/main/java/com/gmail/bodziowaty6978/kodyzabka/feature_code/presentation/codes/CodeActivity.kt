package com.gmail.bodziowaty6978.kodyzabka.feature_code.presentation.codes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.gmail.bodziowaty6978.kodyzabka.R
import com.gmail.bodziowaty6978.kodyzabka.databinding.ActivityCodeBinding
import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.model.Code
import com.gmail.bodziowaty6978.kodyzabka.feature_code.presentation.add_edit_code.AddEditCodeActivity
import com.gmail.bodziowaty6978.kodyzabka.feature_code.presentation.util.OnAdapterItemClickedListener
import com.gmail.bodziowaty6978.kodyzabka.feature_code.presentation.util.SliderAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CodeActivity : AppCompatActivity(), OnAdapterItemClickedListener {

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

        lifecycleScope.launch {
            binding.fabCode.setOnClickListener {
                val intent = Intent(this@CodeActivity,AddEditCodeActivity::class.java)
                startActivity(intent)
            }
        }

        val adapter = SliderAdapter(codeItems, this)
        binding.vp2Code.adapter = adapter

        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { codeState ->
                if(codeState.codes.isNotEmpty()){
                    binding.btScanned.visibility = View.VISIBLE
                }

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

    override fun onAdapterItemClicked(code: Code) {
        val intent = Intent(this,AddEditCodeActivity::class.java).putExtra("clickedCode",code)
        startActivity(intent)
    }
}