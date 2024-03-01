package com.example.tbc_test11.di

import com.example.tbc_test11.data.remote.service.IGetAccountsService
import com.example.tbc_test11.data.remote.service.IGetBankAccountService
import com.example.tbc_test11.data.remote.util.HandleResponse
import com.example.tbc_test11.data.repository.remote.GetAccountsRepositoryImpl
import com.example.tbc_test11.data.repository.remote.GetBankAccountRepositoryImpl
import com.example.tbc_test11.domain.repository.IGetAccountsRepository
import com.example.tbc_test11.domain.repository.IGetBankAccountRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideHandleResponse(): HandleResponse {
        return HandleResponse()
    }

    @Provides
    @Singleton
    fun provideGetAccountsRepository(
        service: IGetAccountsService,
        handleResponse: HandleResponse
    ): IGetAccountsRepository {
        return GetAccountsRepositoryImpl(service, handleResponse)
    }

    @Provides
    @Singleton
    fun provideGetBankAccountRepository(
        service: IGetBankAccountService,
        handleResponse: HandleResponse
    ): IGetBankAccountRepository {
        return GetBankAccountRepositoryImpl(service, handleResponse)
    }
}