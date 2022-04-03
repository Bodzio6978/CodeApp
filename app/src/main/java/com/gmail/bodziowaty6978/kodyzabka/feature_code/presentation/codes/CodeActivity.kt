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
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CodeActivity : AppCompatActivity() {

    private val viewModel: CodesViewModel by viewModels()
    private lateinit var binding: ActivityCodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code)

        binding = ActivityCodeBinding.inflate(layoutInflater,null,false)
        setContentView(binding.root)

        setSupportActionBar(binding.tbCode)

        lifecycleScope.launch {
            binding.btScanned.setOnClickListener {
                binding.vp2Code.apply {
                    currentItem += 1
                }

            }
        }

        val barcodeList = listOf<Code>(
            Code("983470578",System.currentTimeMillis(),"Boguś"),
            Code("123456789",System.currentTimeMillis(),"Kinga"),
            Code("987654321",System.currentTimeMillis(),"Ania"),
            Code("783427678",System.currentTimeMillis(),"Iwonka"),
        )

        val adapter = SliderAdapter(barcodeList)
        binding.vp2Code.adapter = adapter
    }


}