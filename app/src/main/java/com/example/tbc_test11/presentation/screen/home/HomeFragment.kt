package com.example.tbc_test11.presentation.screen.home

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.tbc_test11.databinding.FragmentHomeBinding
import com.example.tbc_test11.presentation.event.HomeFragmentEvents
import com.example.tbc_test11.presentation.model.AccountPresentation
import com.example.tbc_test11.presentation.model.BankAccountPresentation
import com.example.tbc_test11.presentation.screen.base.BaseFragment
import com.example.tbc_test11.presentation.screen.fromBottom.FromBottomSheet
import com.example.tbc_test11.presentation.screen.toBottom.ToBottomSheet
import com.example.tbc_test11.presentation.state.home.HomeFragmentState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val viewModel: HomeFragmentViewModel by viewModels()
    override fun setUp() {
        bindObservers()
        listeners()
    }

    private fun listeners() {
        binding.cardFrom.setOnClickListener {
            val bottomSheet = FromBottomSheet().apply {
                setListener(object : FromBottomSheet.BottomSheetListener {
                    override fun onOptionSelected(option: AccountPresentation) {
                        setChosenAccount(option)
                    }
                })
            }
            bottomSheet.show(childFragmentManager, bottomSheet.tag)
        }

        binding.cardTo.setOnClickListener {
            val bottomSheet = ToBottomSheet().apply {
                setListener(object : ToBottomSheet.BottomSheetListener {
                    override fun onOptionSelected(option: BankAccountPresentation) {
                        setToAccount(option)
                    }
                })
            }
            bottomSheet.show(childFragmentManager, bottomSheet.tag)
        }
    }

    private fun handleState(state: HomeFragmentState) {
        binding.progressBar.visibility = if (state.loading) View.VISIBLE else View.GONE

        state.error?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            viewModel.onEvent(HomeFragmentEvents.ResetError)
        }

        state.chosenAccount?.let {
            setChosenAccount(it)
        }
    }

    private fun setChosenAccount(account: AccountPresentation) = with(binding) {
        tvBalance.text = account.balance.toString()
        tvFourDigits.text = "**** ".plus(account.accNumber.takeLast(4))
    }

    private fun setToAccount(account: BankAccountPresentation) = with(binding) {
        tvBalance2.text = account.valType
        tvFourDigits2.text = "**** ".plus(account.accNumber.takeLast(4))
    }

    private fun bindObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.homeState.collect {
                    handleState(it)
                }
            }
        }
    }
}