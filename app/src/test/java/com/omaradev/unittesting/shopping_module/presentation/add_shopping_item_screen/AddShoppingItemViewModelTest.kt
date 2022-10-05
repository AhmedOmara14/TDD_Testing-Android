package com.omaradev.unittesting.shopping_module.presentation.add_shopping_item_screen

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.omaradev.unittesting.shopping_module.MainCoroutineRule
import com.omaradev.unittesting.shopping_module.common.Constants
import com.omaradev.unittesting.shopping_module.common.Resource
import com.omaradev.unittesting.shopping_module.common.Status
import com.omaradev.unittesting.shopping_module.data.RepositoryTest
import com.omaradev.unittesting.shopping_module.data.local.ShoppingItem
import com.omaradev.unittesting.shopping_module.getOrAwaitValueTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class AddShoppingItemViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()


    private lateinit var addShoppingItemViewModel: AddShoppingItemViewModel

    @Before
    fun setUp() {
        addShoppingItemViewModel = AddShoppingItemViewModel(RepositoryTest())
    }

    @Test
    fun `insert shopping item with empty field, return error`() {
        addShoppingItemViewModel.insertShoppingItem("", "20", "10.5")
        val value: Resource<ShoppingItem??> =
            addShoppingItemViewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with too long name, return error`() {
        var string = buildString {
            for (i in 1..Constants.MAX_LEN_NAME + 1) {
                append(i)
            }
        }
        addShoppingItemViewModel.insertShoppingItem(string, "20", "10.5")
        val value: Resource<ShoppingItem?> =
            addShoppingItemViewModel.insertShoppingItemStatus.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with name less than 2 len, return error`() {
        addShoppingItemViewModel.insertShoppingItem("aa", "20", "10.5")
        val value: Resource<ShoppingItem?> =
            addShoppingItemViewModel.insertShoppingItemStatus.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with too long price, return error`() {
        var string = buildString {
            for (i in 1..Constants.MAX_LEN_NAME + 1) {
                append(i)
            }
        }
        addShoppingItemViewModel.insertShoppingItem("name", "20", string)
        val value: Resource<ShoppingItem?> =
            addShoppingItemViewModel.insertShoppingItemStatus.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with too long amount, return error`() {
        addShoppingItemViewModel.insertShoppingItem(
            "name",
            "1111111111111111111111111111111",
            "10.5"
        )
        val value: Resource<ShoppingItem?> =
            addShoppingItemViewModel.insertShoppingItemStatus.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with valid inputs, return success`() {
        addShoppingItemViewModel.insertShoppingItem(
            "name",
            "10",
            "10.5"
        )
        val value: Resource<ShoppingItem?> =
            addShoppingItemViewModel.insertShoppingItemStatus.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.SUCCESS)
    }
}