package com.omaradev.unittesting

object RegistrationUtil {
    private val users = listOf("omaraDEV", "tester", "Developer")
    fun validateRegisterInput(
        userName: String,
        password: String,
        confirmedPassword: String
    ): Boolean {
        if (userName.isEmpty() || password.isEmpty()) {
            return false
        } else if (userName in users) {
            return false
        } else if (password != confirmedPassword) {
            return false
        } else if (password.count { it.isDigit() } < 2) {
            return false
        } else {
            return true
        }
    }
}