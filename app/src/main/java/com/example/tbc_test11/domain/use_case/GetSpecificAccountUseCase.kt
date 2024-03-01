package com.example.tbc_test11.domain.use_case

import com.example.tbc_test11.data.common.Resource
import com.example.tbc_test11.domain.model.Account
import com.example.tbc_test11.domain.repository.IGetAccountsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSpecificAccountUseCase @Inject constructor(private val repository: IGetAccountsRepository) {

    suspend operator fun invoke(id: Int): Flow<Resource<Account>> {
        return flow {
            repository.getAccounts().map { resource ->
                when (resource) {
                    is Resource.Success -> {
                        emit(Resource.Success(filterAccount(resource.data, id)))
                    }

                    is Resource.Error -> {
                        emit(Resource.Error(resource.error))
                    }

                    is Resource.Loading -> {
                        emit(Resource.Loading(resource.loading))
                    }
                }
            }
        }
    }

    private fun filterAccount(list: List<Account>, id: Int): Account {
        for (i in list) {
            if (i.id == id) {
                return i
            }
        }
        return Account(0, "", "", "", "", 0, "")
    }
}