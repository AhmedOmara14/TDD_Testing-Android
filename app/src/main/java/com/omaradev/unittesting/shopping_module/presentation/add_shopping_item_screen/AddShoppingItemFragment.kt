package com.omaradev.unittesting.shopping_module.presentation.add_shopping_item_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.omaradev.unittesting.R

class AddShoppingItemFragment : Fragment(R.layout.fragment_add_shopping_item) {
    private lateinit var viewModel :AddShoppingItemViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(AddShoppingItemViewModel::class.java)
    }
}