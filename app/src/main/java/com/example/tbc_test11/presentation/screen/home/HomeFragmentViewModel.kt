package com.example.tbc_test11.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbc_test11.data.common.Resource
import com.example.tbc_test11.domain.use_case.GetAccountsUseCase
import com.example.tbc_test11.domain.use_case.GetBankAccountUseCase
import com.example.tbc_test11.domain.use_case.GetSpecificAccountUseCase
import com.example.tbc_test11.presentation.event.HomeFragmentEvents
import com.example.tbc_test11.presentation.mapper.toPres
import com.example.tbc_test11.presentation.state.home.HomeFragmentState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val getAccounts: GetAccountsUseCase,
    private val getBankAccount: GetBankAccountUseCase,
    private val getSpecificAccount: GetSpecificAccountUseCase
) : ViewModel() {

    private val _homeState = MutableStateFlow(HomeFragmentState())
    val homeState: SharedFlow<HomeFragmentState> = _homeState.asStateFlow()


    fun onEvent(event: HomeFragmentEvents) {
        when (event) {
            is HomeFragmentEvents.FetchAccounts -> fetchAccounts()
            is HomeFragmentEvents.FetchBankAccount -> fetchBankAccount()
            is HomeFragmentEvents.ResetError -> setError(null)
            is HomeFragmentEvents.GetSpecificAccount -> {fetchSpecific(event.id)}
            else -> {}
        }
    }

    private fun fetchSpecific(id: Int){
        viewModelScope.launch {
            getSpecificAccount(id).collect{
                when (it) {
                    is Resource.Success -> {
                        _homeState.update { currentState -> currentState.copy(chosenAccount = it.data.toPres()) }
                    }

                    is Resource.Error -> {
                        setError(it.error)
                    }

                    is Resource.Loading -> {
                        _homeState.update { currentState -> currentState.copy(loading = it.loading) }
                    }
                }
            }
        }
    }

    private fun fetchAccounts() {
        viewModelScope.launch {
            getAccounts().collect {
                when (it) {
                    is Resource.Success -> {
                        _homeState.update { currentState -> currentState.copy(accounts = it.data.map { it.toPres() }) }
                    }

                    is Resource.Error -> {
                        setError(it.error)
                    }

                    is Resource.Loading -> {
                        _homeState.update { currentState -> currentState.copy(loading = it.loading) }
                    }
                }
            }
        }
    }

    private fun fetchBankAccount() {
        viewModelScope.launch {
            getBankAccount().collect {
                when (it) {
                    is Resource.Success -> {
                        _homeState.update { currentState -> currentState.copy(bankAccount = it.data.toPres()) }
                    }

                    is Resource.Error -> {
                        setError(it.error)
                    }

                    is Resource.Loading -> {
                        _homeState.update { currentState -> currentState.copy(loading = it.loading) }
                    }
                }
            }
        }
    }

    private fun setError(error: String?) {
        viewModelScope.launch {
            _homeState.update { currentState -> currentState.copy(error = error) }
        }
    }
}