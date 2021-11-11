package com.timkwali.imagesearch.presentation.ui.imagedetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.timkwali.imagesearch.R
import com.timkwali.imagesearch.databinding.FragmentImageDetailsBinding
import com.timkwali.imagesearch.databinding.FragmentImageListBinding
import com.timkwali.imagesearch.presentation.ui.imagelist.ImageListViewModel
import com.timkwali.imagesearch.presentation.utils.ClickHandlers

class ImageDetailsFragment : Fragment() {

    private lateinit var binding: FragmentImageDetailsBinding
    private val imageListViewModel: ImageListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_image_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageListViewModel.getImageById(4)
        binding.apply {
            clickHandlers = ClickHandlers
            imageItem = imageListViewModel.imageItem.value
        }
    }
}