package com.gmail.bodziowaty6978.kodyzabka.feature_code.presentation.codes_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.bodziowaty6978.kodyzabka.R
import com.gmail.bodziowaty6978.kodyzabka.databinding.ActivityCodesListBinding
import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.model.Code
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CodesListActivity : AppCompatActivity(), OnAdapterItemClickedListener {

    private lateinit var binding: ActivityCodesListBinding
    private val viewModel: CodesListViewModel by viewModels()

    private val codeItems = mutableListOf<Code>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_codes_list)

        binding = ActivityCodesListBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        binding.rvCodesList.adapter = CodesListAdapter(codeItems, this)
        binding.rvCodesList.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            binding.ibBack.setOnClickListener {
                super.onBackPressed()
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.codes.collectLatest { codes ->
                codeItems.clear()
                codeItems.addAll(codes)
                binding.rvCodesList.adapter?.notifyDataSetChanged()

//                codes.forEach { code ->
//                    if (!codeItems.contains(code)) {
//                        codeItems.add(code)
//                        binding.rvCodesList.adapter?.apply {
//                            notifyItemInserted(codeItems.size - 1)
//                        }
//                    }
//                }
//                codeItems.forEachIndexed { index, code ->
//                    if (!codes.contains(code)) {
//                        codeItems.remove(code)
//                        binding.rvCodesList.adapter?.apply {
//                            notifyItemRemoved(index)
//                        }
//                    }
//                }

            }
        }
    }

    private fun onEvent(codeEvent: CodeEvent){
        viewModel.onEvent(codeEvent)
        when(codeEvent){
            is CodeEvent.DeleteCode -> {
                Snackbar.make(binding.clCodeList,resources.getString(R.string.usunieto_kod),Snackbar.LENGTH_LONG).setAction(resources.getString(R.string.przywroc)){
                    viewModel.onEvent(CodeEvent.RestoreCode)
                    onEvent(CodeEvent.RestoreCode)
                }.show()
            }
            is CodeEvent.EditCode -> {

            }
            is CodeEvent.RestoreCode-> {
                Snackbar.make(binding.clCodeList,resources.getString(R.string.przywrocono_kod),Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun onAdapterItemClicked(codeEvent: CodeEvent) {
        onEvent(codeEvent)
    }
}