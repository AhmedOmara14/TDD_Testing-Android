package com.omaradev.unittesting.shopping_module.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.omaradev.unittesting.shopping_module.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest //refer to unit test
class ShoppingDaoTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var shoppingItemDatabase: ShoppingItemDatabase
    private lateinit var shoppingDao: ShoppingDao

    @Before
    fun setUp() {
        //MemoryDatabaseBuilder will save data in memory not storage
        shoppingItemDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ShoppingItemDatabase::class.java
        ).allowMainThreadQueries().build()
        shoppingDao = shoppingItemDatabase.shoppingDao()
    }

    //Close Database
    @After
    fun teardown() {
        shoppingItemDatabase.close()
    }

    @Test
    fun insertShoppingItemTest() = runBlockingTest {
        val shoppingItem = ShoppingItem(0, "test", 10, 100f, "")

        shoppingDao.insertShoppingItem(shoppingItem)
        val allShoppingItems = shoppingDao.getAllShoppingItems().getOrAwaitValue()

        assertThat(allShoppingItems).contains(shoppingItem)

    }

    @Test
    fun deleteShoppingItemTest() = runBlockingTest {
        val shoppingItem = ShoppingItem(0, "TEST", 20, 20f, "")
        shoppingDao.insertShoppingItem(shoppingItem)
        shoppingDao.deleteShoppingItem(shoppingItem)

        val allShoppingItems = shoppingDao.getAllShoppingItems().getOrAwaitValue()

        assertThat(allShoppingItems).doesNotContain(shoppingItem)
    }

    @Test
    fun getTotalPriceOfAllShoppingItemsTest() = runBlockingTest {
        val shoppingItem_1 = ShoppingItem(0, "TEST1", 2, 10f, "")
        val shoppingItem_2 = ShoppingItem(1, "TEST2", 4, 5.5f, "")
        val shoppingItem_3 = ShoppingItem(2, "TEST3", 1, 7f, "")

        shoppingDao.insertShoppingItem(shoppingItem_1)
        shoppingDao.insertShoppingItem(shoppingItem_2)
        shoppingDao.insertShoppingItem(shoppingItem_3)

        val totalPrice = shoppingDao.getTotalPriceOfItems().getOrAwaitValue()

        assertThat(totalPrice).isEqualTo(2 * 10f + 4 * 5.5f + 1 * 7f)


    }
}