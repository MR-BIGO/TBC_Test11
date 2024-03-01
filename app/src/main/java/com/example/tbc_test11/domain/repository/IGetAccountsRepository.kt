package com.example.tbc_test11.domain.repository

import com.example.tbc_test11.data.common.Resource
import com.example.tbc_test11.domain.model.Account
import kotlinx.coroutines.flow.Flow

interface IGetAccountsRepository {

    suspend fun getAccounts(): Flow<Resource<List<Account>>>
}