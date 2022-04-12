package com.gmail.bodziowaty6978.kodyzabka.feature_code.presentation.add_edit_code

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.SwitchCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.gmail.bodziowaty6978.kodyzabka.feature_code.presentation.codes.CodeActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class AddEditFragment : Fragment() {

    private lateinit var viewModel:AddEditViewModel

    private lateinit var etCode:EditText
    private lateinit var etCodeOwner:EditText
    private lateinit var fabSave:FloatingActionButton
    private lateinit var ibBarcode:ImageButton
    private lateinit var rlAdd:RelativeLayout
    private lateinit var scCode:SwitchCompat

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(requireActivity()).get(AddEditViewModel::class.java)

        val view = inflater.inflate(R.layout.fragment_add_edit,container,false)

        etCode = view.findViewById(R.id.etCode)
        etCodeOwner = view.findViewById(R.id.etCodeOwner)
        fabSave = view.findViewById(R.id.fabSave)
        ibBarcode = view.findViewById(R.id.ibBarcode)
        rlAdd = view.findViewById(R.id.rlAddEdit)
        scCode = view.findViewById(R.id.scCode)

        collectBarcodeState()
        collectCodeEventState()

        lifecycleScope.launch {
            val requestCamera = registerForActivityResult(ActivityResultContracts.RequestPermission()){ isGranted ->
                if (!isGranted){
                    Toast.makeText(requireActivity(),
                        resources.getString(R.string.musisz_pozwolic_na_dostep_do_kamery_jesli),
                        Toast.LENGTH_LONG).show()
                }
            }
            requestCamera.launch(android.Manifest.permission.CAMERA)
        }

        lifecycleScope.launch {
            fabSave.setOnClickListener {
                viewModel.saveCode(
                    Code(
                        code = etCode.text.toString().trim(),
                        timeStamp = System.currentTimeMillis(),
                        user = etCodeOwner.text.toString().trim()
                    )
                )
            }
        }

        lifecycleScope.launch {
            ibBarcode.setOnClickListener {
                findNavController().navigate(R.id.scannerFragment)
            }
        }
        return view
    }

    private fun collectCodeEventState(){
        lifecycleScope.launchWhenStarted {
            viewModel.codeEventState.collect {
                when(it){
                    is AddEditCodeEvent.SaveCode -> {

                        if (!scCode.isChecked){
                            startActivity(Intent(requireContext(),CodeActivity::class.java))
                            activity?.finish()
                        }

                        Snackbar.make(rlAdd,resources.getString(R.string.dodano_nowy_kod),
                            Snackbar.LENGTH_LONG).show()

                        etCodeOwner.text.clear()
                        etCode.text.clear()
                    }
                    is AddEditCodeEvent.ShowSnackbar -> {
                        Snackbar.make(rlAdd,it.message, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun collectBarcodeState(){
        lifecycleScope.launchWhenStarted {
            viewModel.barcodeState.collectLatest { code ->
                etCode.setText(code)
            }
        }
    }
}