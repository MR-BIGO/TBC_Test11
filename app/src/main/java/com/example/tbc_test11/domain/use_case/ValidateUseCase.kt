package com.example.tbc_test11.domain.use_case

import com.example.tbc_test11.data.common.Resource
import com.example.tbc_test11.domain.model.BankAccount
import com.example.tbc_test11.domain.repository.IGetBankAccountRepository
import com.example.tbc_test11.domain.util.ValidationUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class ValidateUseCase @Inject constructor(
    private val repository: IGetBankAccountRepository,
    private val validationUtil: ValidationUtil
) {

    suspend operator fun invoke(
        accNum: String,
        idNum: String,
        phoneNum: String
    ): Flow<Resource<BankAccount>> {
        if (validationUtil.isAccountNumberValid(accNum)) {
            return flowOf(Resource.Error("Invalid Account Number"))
        }

        if (!validationUtil.isUserIdValid(idNum)) {
            return flowOf(Resource.Error("Invalid ID Number"))
        }

        if (!validationUtil.isPhoneNumberValid(phoneNum)) {
            return flowOf(Resource.Error("Invalid Phone Number"))
        }

        return repository.getBankAccount()
    }
}
