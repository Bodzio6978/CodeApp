package com.gmail.bodziowaty6978.kodyzabka.feature_code.presentation.add_edit_code

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.gmail.bodziowaty6978.kodyzabka.R
import com.gmail.bodziowaty6978.kodyzabka.databinding.ActivityAddEditCodeBinding
import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.model.Code
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddEditCodeActivity : AppCompatActivity() {

    private lateinit var binding:ActivityAddEditCodeBinding
    private val viewModel:AddEditViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_code)

        binding = ActivityAddEditCodeBinding.inflate(layoutInflater,null,false)
        setContentView(binding.root)



        lifecycleScope.launch {
            val requestCamera = registerForActivityResult(ActivityResultContracts.RequestPermission()){ isGranted ->
                if (!isGranted){
                    Snackbar.make(binding.rlAddEdit,
                        resources.getString(R.string.musisz_pozwolic_na_dostep_do_kamery_jesli),
                        Snackbar.LENGTH_LONG)
                        .show()
                }
            }

            requestCamera.launch(android.Manifest.permission.CAMERA)
        }



        lifecycleScope.launchWhenStarted {
            viewModel.codeEventState.collect {
                when(it){
                    is AddEditCodeEvent.SaveCode -> {
                        Snackbar.make(binding.rlAddEdit,resources.getString(R.string.dodano_nowy_kod),Snackbar.LENGTH_LONG).show()
                    }
                    is AddEditCodeEvent.ShowSnackbar -> {
                        Snackbar.make(binding.rlAddEdit,it.message,Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}