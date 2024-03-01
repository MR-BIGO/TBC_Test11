package com.example.tbc_test11.domain.util

import javax.inject.Inject

class ValidationUtil @Inject constructor(){
    fun isAccountNumberValid(accountNumber: String): Boolean {
        return accountNumber.length == 23
    }

    fun isUserIdValid(userId: String): Boolean {
        return userId.length == 11
    }

    fun isPhoneNumberValid(phoneNumber: String): Boolean {
        val sanitizedPhoneNumber = phoneNumber.replace("-", "")
        return sanitizedPhoneNumber.length == 9
    }
}