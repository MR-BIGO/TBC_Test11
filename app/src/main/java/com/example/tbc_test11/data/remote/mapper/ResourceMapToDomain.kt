package com.example.tbc_test11.data.remote.mapper

import com.example.tbc_test11.data.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun <T, DomainType> Flow<Resource<T>>.mapToDomain(mapper: (T) -> DomainType): Flow<Resource<DomainType>> {
    return this.map { resource ->
        when (resource) {
            is Resource.Success -> Resource.Success(resource.data.let(mapper))
            is Resource.Loading -> Resource.Loading(resource.loading)
            is Resource.Error -> Resource.Error(resource.error)
        }
    }
}