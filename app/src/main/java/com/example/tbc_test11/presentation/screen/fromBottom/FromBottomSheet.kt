package com.example.tbc_test11.presentation.screen.fromBottom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbc_test11.databinding.FragmentBottonSheetBinding
import com.example.tbc_test11.presentation.event.HomeFragmentEvents
import com.example.tbc_test11.presentation.model.AccountPresentation
import com.example.tbc_test11.presentation.screen.home.HomeFragment
import com.example.tbc_test11.presentation.state.home.HomeFragmentState
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FromBottomSheet : BottomSheetDialogFragment() {

    private lateinit var homeFragment: HomeFragment

    interface BottomSheetListener {
        fun onOptionSelected(option: AccountPresentation)
    }
    private var listener: BottomSheetListener? = null
    fun setListener(listener: BottomSheetListener) {
        this.listener = listener
    }

    private var _binding: FragmentBottonSheetBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FromBottomSheetViewModel by viewModels()
    private lateinit var accountRecyclerAdapter: AccountRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBottonSheetBinding.inflate(inflater, container, false)
        homeFragment = HomeFragment()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        viewModel.onEvent(HomeFragmentEvents.FetchAccounts)
        setUpRv()
        listeners()
        bindObservers()
    }

    private fun listeners(){
        accountRecyclerAdapter.itemOnClick = {
            listener?.onOptionSelected(it)
            dismiss()
        }
    }

    private fun setUpRv(){
        accountRecyclerAdapter = AccountRecyclerAdapter()
        binding.rvAccounts.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = accountRecyclerAdapter
        }
    }

    private fun handleState(state: HomeFragmentState){
        binding.progressBar.visibility = if (state.loading) View.VISIBLE else View.GONE

        state.accounts?.let {
            accountRecyclerAdapter.setData(it)
        }

        state.error?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            viewModel.onEvent(HomeFragmentEvents.ResetError)
        }
    }

    private fun bindObservers(){
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