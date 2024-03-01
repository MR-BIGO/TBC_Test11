package com.example.tbc_test11.presentation.screen.toBottom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.tbc_test11.databinding.ToBottomSheetBinding
import com.example.tbc_test11.presentation.event.HomeFragmentEvents
import com.example.tbc_test11.presentation.model.BankAccountPresentation
import com.example.tbc_test11.presentation.screen.home.HomeFragment
import com.example.tbc_test11.presentation.state.home.HomeFragmentState
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ToBottomSheet : BottomSheetDialogFragment() {

    private lateinit var homeFragment: HomeFragment

    interface BottomSheetListener {
        fun onOptionSelected(option: BankAccountPresentation)
    }

    private var listener: BottomSheetListener? = null
    fun setListener(listener: BottomSheetListener) {
        this.listener = listener
    }

    private var _binding: ToBottomSheetBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ToBottomSheetViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ToBottomSheetBinding.inflate(inflater, container, false)
        homeFragment = HomeFragment()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        listeners()
        bindObservers()
    }

    private fun listeners() = with(binding) {
        btnChoose.setOnClickListener {
            viewModel.onEvent(
                HomeFragmentEvents.ValidateFields(
                    etAccountNumber.text.toString(),
                    etPhone.text.toString(),
                    etIdNumber.text.toString()
                )
            )
        }
    }

    private fun handleState(state: HomeFragmentState){
        //aq chedavs mgoni ragacas
        state.toAccount?.let {
            listener?.onOptionSelected(it)
            dismiss()
        }
    }

    private fun bindObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.homeState.collect{
                    handleState(it)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}