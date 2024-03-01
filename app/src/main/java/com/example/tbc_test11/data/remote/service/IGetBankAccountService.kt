package com.example.tbc_test11.data.remote.service

import com.example.tbc_test11.data.remote.model.BankAccountDto
import retrofit2.Response
import retrofit2.http.GET

interface IGetBankAccountService {

    @GET("")
    suspend fun getBankAccount(): Response<BankAccountDto>
}