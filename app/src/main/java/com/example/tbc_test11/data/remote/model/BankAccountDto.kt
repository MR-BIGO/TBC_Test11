package com.example.tbc_test11.data.remote.model

import com.squareup.moshi.Json

data class BankAccountDto(
    val id: Int,
    @Json(name = "account_name")
    val accName: String,
    @Json(name = "account_number")
    val accNumber: String,
    @Json(name = "valute_type")
    val valType: String,
    @Json(name = "card_type")
    val cardType: String,
    @Json(name = "card_logo")
    val cardLogo: String?
)
