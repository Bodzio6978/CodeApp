package com.gmail.bodziowaty6978.kodyzabka.feature_code.presentation.codes

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.gmail.bodziowaty6978.kodyzabka.R
import com.gmail.bodziowaty6978.kodyzabka.databinding.ActivityCodeBinding
import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.model.Code
import com.gmail.bodziowaty6978.kodyzabka.feature_code.presentation.add_edit_code.AddEditCodeActivity
import com.gmail.bodziowaty6978.kodyzabka.feature_code.presentation.codes_list.CodesListActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
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

        setUpRecyclerView()

        lifecycleScope.launch {
            binding.ibEditCode.setOnClickListener {
                startActivity(Intent(this@CodeActivity, CodesListActivity::class.java))
            }
        }

        lifecycleScope.launch {
            binding.btScanned.setOnClickListener {
                binding.vp2Code.apply {
                    currentItem += 1
                }

            }
        }

        lifecycleScope.launch {
            binding.fabCode.setOnClickListener {
                val intent = Intent(this@CodeActivity, AddEditCodeActivity::class.java)
                startActivity(intent)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpRecyclerView(){
        val adapter = SliderAdapter(codeItems)
        binding.vp2Code.adapter = adapter

        lifecycleScope.launch {
            binding.vp2Code.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    viewModel.updateCode(
                        code = codeItems[position].copy(
                            timeStamp = System.currentTimeMillis()
                        )
                    )
                }

            })
        }

        lifecycleScope.launchWhenStarted {
            viewModel.codes.collectLatest { codes ->
                if (codes.isNotEmpty()) {
                    binding.btScanned.visibility = View.VISIBLE
                }

                codeItems.clear()
                codeItems.addAll(codes)
                binding.vp2Code.adapter?.notifyDataSetChanged()
            }
        }
    }

    override fun onStart() {
        viewModel.getCodes()
        super.onStart()
    }
}