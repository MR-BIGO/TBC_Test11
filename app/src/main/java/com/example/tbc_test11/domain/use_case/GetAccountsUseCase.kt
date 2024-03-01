package com.example.tbc_test11.domain.use_case

import com.example.tbc_test11.data.common.Resource
import com.example.tbc_test11.domain.model.Account
import com.example.tbc_test11.domain.repository.IGetAccountsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAccountsUseCase @Inject constructor(private val repository: IGetAccountsRepository) {

    suspend operator fun invoke(): Flow<Resource<List<Account>>>{
        return repository.getAccounts()
    }
}