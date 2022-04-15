package com.gmail.bodziowaty6978.kodyzabka.feature_code.presentation.add_edit_code

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.gmail.bodziowaty6978.kodyzabka.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddEditCodeActivity : AppCompatActivity() {

    private val viewModel:AddEditViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_code)

        val editedCodeId = intent?.getIntExtra("codeId",0)
        viewModel.getEditedCode(editedCodeId)
    }
}