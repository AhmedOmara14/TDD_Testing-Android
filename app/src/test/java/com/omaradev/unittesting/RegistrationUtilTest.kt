package com.omaradev.unittesting

import org.junit.Test
import com.google.common.truth.Truth.assertThat

class RegistrationUtilTest {
    /**
     * Test Cases For Register Form
     * the input is not valid if ...
     * ... the user name is already taken
     * ... password and confirm password is not the the same
     * ... password contains less than 2 digits
     **/

    @Test
    fun `empty userName return false`() {
        val result =
            RegistrationUtil.validateRegisterInput(
                "",
                "test",
                "test"
            )

        assertThat(result).isFalse()
    }
    @Test
    fun `empty password return false`() {
        val result =
            RegistrationUtil.validateRegisterInput(
                "omaraDEV",
                "",
                ""
            )
        assertThat(result).isFalse()
    }
    @Test
    fun `valid userName and correctly repeated password return true`() {
        val result =
            RegistrationUtil.validateRegisterInput(
                "DEV",
                "test123",
                "test123"
            )
        assertThat(result).isTrue()
    }
    @Test
    fun `userName already exists return false`() {
        val result =
            RegistrationUtil.validateRegisterInput(
                "omaraDEV",
                "test",
                "test"
            )
        assertThat(result).isFalse()
    }
    @Test
    fun `password and confirm password is not the same false`() {
        val result =
            RegistrationUtil.validateRegisterInput(
                "omaraDEV",
                "test",
                "test123"
            )
        assertThat(result).isFalse()
    }

    @Test
    fun `password is less than 2 digits return false`(){
        val result =
            RegistrationUtil.validateRegisterInput(
                "omaraDEV",
                "test",
                "test"
            )
        assertThat(result).isFalse()
    }
}