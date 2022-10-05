package com.omaradev.unittesting.shopping_module.presentation.image_pick_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.omaradev.unittesting.R

class ImagePickFragment :Fragment(R.layout.fragment_image_pick) {
    private lateinit var viewModel : ImagePickViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ImagePickViewModel::class.java)
    }
}