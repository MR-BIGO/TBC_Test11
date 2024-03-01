package com.example.tbc_test11.domain.use_case

import com.example.tbc_test11.data.common.Resource
import com.example.tbc_test11.domain.model.BankAccount
import com.example.tbc_test11.domain.repository.IGetBankAccountRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBankAccountUseCase @Inject constructor(private val repository: IGetBankAccountRepository) {

    suspend operator fun invoke(): Flow<Resource<BankAccount>>{
        return repository.getBankAccount()
    }
}