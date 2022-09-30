package com.omaradev.unittesting

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class ResourceCompareTest {
    private lateinit var resourceCompare: ResourceCompare
    @Before
    fun setUp() {
        resourceCompare = ResourceCompare()
    }

    @Test
    fun `stringisequalresourcestringreturntrue`(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceCompare.isEqual(context,R.string.app_name,"Unit Testing")

        assertThat(result).isTrue()
    }
    @Test
    fun `stringisequalresourcestringreturnfalse`(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceCompare.isEqual(context,R.string.app_name,"")

        assertThat(result).isTrue()
    }

}