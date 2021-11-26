package com.timkwali.imagesearch.presentation.ui.imagedetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.timkwali.imagesearch.R
import com.timkwali.imagesearch.common.Resource
import com.timkwali.imagesearch.databinding.FragmentImageDetailsBinding
import com.timkwali.imagesearch.domain.model.ImageItem
import com.timkwali.imagesearch.presentation.ui.imagelist.ImageListViewModel
import com.timkwali.imagesearch.presentation.utils.ClickHandlers
import com.timkwali.imagesearch.presentation.utils.showSnackBar

class ImageDetailsFragment : Fragment() {

    private lateinit var binding: FragmentImageDetailsBinding
    private val args: ImageDetailsFragmentArgs by navArgs()
    private lateinit var image: ImageItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_image_details, container, false)
        binding.apply {
            image = args.imageItem
            clickHandlers = ClickHandlers
            imageItem = image
        }
        return binding.root
    }
}