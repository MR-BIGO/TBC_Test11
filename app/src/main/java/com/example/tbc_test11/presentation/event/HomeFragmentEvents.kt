package com.example.tbc_test11.presentation.event

sealed class HomeFragmentEvents{
    data object FetchAccounts: HomeFragmentEvents()
    data object FetchBankAccount: HomeFragmentEvents()
    data object ResetError: HomeFragmentEvents()
    data class GetSpecificAccount(val id: Int): HomeFragmentEvents()
    data class ValidateFields(val accNum: String, val phoneNum: String, val idNum: String): HomeFragmentEvents()
}
