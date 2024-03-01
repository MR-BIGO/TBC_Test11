package com.example.tbc_test11.presentation.screen.toBottom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbc_test11.data.common.Resource
import com.example.tbc_test11.domain.use_case.ValidateUseCase
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
class ToBottomSheetViewModel @Inject constructor(private val validate: ValidateUseCase): ViewModel() {

    private val _homeState = MutableStateFlow(HomeFragmentState())
    val homeState: SharedFlow<HomeFragmentState> = _homeState.asStateFlow()

    fun onEvent(event: HomeFragmentEvents){
        when(event){
            is HomeFragmentEvents.ValidateFields -> {validateFields(event.accNum, event.idNum, event.phoneNum)}
            else -> {}
        }
    }

    private fun validateFields(accNum: String, idNum: String, phoneNum: String){
        viewModelScope.launch {
            validate(accNum, idNum, phoneNum).collect{
                when (it) {
                    is Resource.Success -> {
                        _homeState.update { currentState -> currentState.copy(toAccount = it.data.toPres()) }
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

    private fun setError(error: String?){
        viewModelScope.launch {
            _homeState.update { currentState -> currentState.copy(error = error) }
        }
    }
}