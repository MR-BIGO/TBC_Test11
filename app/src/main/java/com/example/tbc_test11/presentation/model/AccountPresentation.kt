package com.example.tbc_test11.presentation.model

data class AccountPresentation(
    val id: Int,
    val accName: String,
    val accNumber: String,
    val valType: String,
    val cardType: String,
    val balance: Int,
    val cardLogo: String?
)
