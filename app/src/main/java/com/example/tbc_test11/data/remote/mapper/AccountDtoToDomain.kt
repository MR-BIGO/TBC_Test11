package com.example.tbc_test11.data.remote.mapper

import com.example.tbc_test11.data.remote.model.AccountDto
import com.example.tbc_test11.domain.model.Account

fun AccountDto.toDomain(): Account{
    return Account(
        id = id,
        accName = accName,
        accNumber = accNumber,
        valType = valType,
        cardType = cardType,
        balance = balance,
        cardLogo = cardLogo
    )
}