package com.example.tbc_test11.presentation.mapper

import com.example.tbc_test11.domain.model.Account
import com.example.tbc_test11.presentation.model.AccountPresentation

fun Account.toPres(): AccountPresentation {
    return AccountPresentation(
        id = id,
        accName = accName,
        accNumber = accNumber,
        valType = valType,
        cardType = cardType,
        balance = balance,
        cardLogo = cardLogo
    )
}