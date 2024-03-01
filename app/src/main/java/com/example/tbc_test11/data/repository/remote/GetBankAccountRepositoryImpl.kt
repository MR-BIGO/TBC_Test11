package com.example.tbc_test11.data.repository.remote

import com.example.tbc_test11.data.common.Resource
import com.example.tbc_test11.data.remote.mapper.mapToDomain
import com.example.tbc_test11.data.remote.mapper.toDomain
import com.example.tbc_test11.data.remote.service.IGetBankAccountService
import com.example.tbc_test11.data.remote.util.HandleResponse
import com.example.tbc_test11.domain.model.BankAccount
import com.example.tbc_test11.domain.repository.IGetBankAccountRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBankAccountRepositoryImpl @Inject constructor(
    private val service: IGetBankAccountService,
    private val handler: HandleResponse
) : IGetBankAccountRepository {
    override suspend fun getBankAccount(): Flow<Resource<BankAccount>> {
        return handler.safeApiCall { service.getBankAccount() }.mapToDomain { it.toDomain() }
    }
}