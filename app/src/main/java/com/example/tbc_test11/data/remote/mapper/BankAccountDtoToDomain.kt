package com.example.tbc_test11.data.remote.mapper

import com.example.tbc_test11.data.remote.model.BankAccountDto
import com.example.tbc_test11.domain.model.BankAccount

fun BankAccountDto.toDomain(): BankAccount{
    return BankAccount(
        id = id,
        accName = accName,
        accNumber = accNumber,
        valType = valType,
        cardType = cardType,
        cardLogo = cardLogo
    )
}