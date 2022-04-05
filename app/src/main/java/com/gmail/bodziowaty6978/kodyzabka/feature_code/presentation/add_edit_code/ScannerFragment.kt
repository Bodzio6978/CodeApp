package com.gmail.bodziowaty6978.kodyzabka.feature_code.presentation.add_edit_code

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.gmail.bodziowaty6978.kodyzabka.R
import com.gmail.bodziowaty6978.kodyzabka.databinding.FragmentAddEditBinding
import com.gmail.bodziowaty6978.kodyzabka.databinding.FragmentScannerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScannerFragment : Fragment() {

    private lateinit var viewModel:AddEditViewModel

    private lateinit var codeScanner:CodeScanner
    private lateinit var scannerView:CodeScannerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_scanner,container,false)
        viewModel = ViewModelProvider(requireActivity()).get(AddEditViewModel::class.java)

        scannerView = view.findViewById(R.id.scanner_view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        codeScanner = CodeScanner(requireActivity(), scannerView)
        codeScanner.decodeCallback = DecodeCallback {
            activity?.runOnUiThread {
                findNavController().navigate(R.id.addEditFragment)
                viewModel.setCodeState(it.text)
            }
        }
        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }
}