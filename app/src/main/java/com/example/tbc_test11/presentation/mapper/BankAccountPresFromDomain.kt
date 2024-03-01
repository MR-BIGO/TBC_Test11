package com.example.tbc_test11.presentation.mapper

import com.example.tbc_test11.domain.model.BankAccount
import com.example.tbc_test11.presentation.model.BankAccountPresentation

fun BankAccount.toPres(): BankAccountPresentation{
    return BankAccountPresentation(
        id = id,
        accName = accName,
        accNumber = accNumber,
        valType = valType,
        cardType = cardType,
        cardLogo = cardLogo
    )
}