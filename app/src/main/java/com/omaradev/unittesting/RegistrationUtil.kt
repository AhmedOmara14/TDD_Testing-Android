package com.omaradev.unittesting

object RegistrationUtil {
    /**
     * Test Cases For Register Form
     * the input is not valid if ...
     * ... the userName/passWord is empty
     * ... the user name is already taken
     * ... password and confirm password is not the the same
     * ... password contains less than 2 digits
     **/

    fun validateRegisterInput(
        userName:String,
        password:String,
        confirmedPassword:String
    ): Boolean {
        return true
    }
}