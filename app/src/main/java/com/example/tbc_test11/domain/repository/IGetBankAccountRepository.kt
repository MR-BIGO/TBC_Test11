package com.example.tbc_test11.domain.repository

import com.example.tbc_test11.data.common.Resource
import com.example.tbc_test11.domain.model.BankAccount
import kotlinx.coroutines.flow.Flow


interface IGetBankAccountRepository {

    suspend fun getBankAccount(): Flow<Resource<BankAccount>>
}