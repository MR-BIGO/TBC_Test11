package com.example.tbc_test11.data.repository.remote

import com.example.tbc_test11.data.common.Resource
import com.example.tbc_test11.data.remote.mapper.mapListToDomain
import com.example.tbc_test11.data.remote.mapper.toDomain
import com.example.tbc_test11.data.remote.service.IGetAccountsService
import com.example.tbc_test11.data.remote.util.HandleResponse
import com.example.tbc_test11.domain.model.Account
import com.example.tbc_test11.domain.repository.IGetAccountsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAccountsRepositoryImpl @Inject constructor(
    private val service: IGetAccountsService,
    private val handler: HandleResponse
) : IGetAccountsRepository {
    override suspend fun getAccounts(): Flow<Resource<List<Account>>> {
        return handler.safeApiCall { service.getAccounts() }.mapListToDomain { it.toDomain() }
    }
}