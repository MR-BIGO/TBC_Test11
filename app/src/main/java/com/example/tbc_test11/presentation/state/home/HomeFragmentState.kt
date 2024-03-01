package com.example.tbc_test11.presentation.state.home

import com.example.tbc_test11.presentation.model.AccountPresentation
import com.example.tbc_test11.presentation.model.BankAccountPresentation

data class HomeFragmentState (
    val accounts: List<AccountPresentation>? = null,
    val chosenAccount: AccountPresentation? = null,
    val toAccount: BankAccountPresentation? = null,
    val bankAccount: BankAccountPresentation? = null,
    val error: String? = null,
    val loading: Boolean = false
)