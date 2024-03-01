package com.example.tbc_test11.domain.model

data class BankAccount(
    val id: Int,
    val accName: String,
    val accNumber: String,
    val valType: String,
    val cardType: String,
    val cardLogo: String?
)
