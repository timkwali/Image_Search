package com.timkwali.imagesearch.presentation.ui.imagelist

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.timkwali.imagesearch.R
import com.timkwali.imagesearch.common.Resource
import com.timkwali.imagesearch.databinding.FragmentImageListBinding
import com.timkwali.imagesearch.domain.model.ImageItem
import com.timkwali.imagesearch.presentation.adapter.ImageListRvAdapter
import com.timkwali.imagesearch.presentation.utils.ClickListener
import com.timkwali.imagesearch.presentation.utils.showSnackBar
import com.timkwali.imagesearch.presentation.utils.showYesNoDialog
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Error

@AndroidEntryPoint
class ImageListFragment : Fragment(), ClickListener<ImageItem> {

    private lateinit var binding: FragmentImageListBinding
    private val imageListViewModel: ImageListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_image_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = imageListViewModel
        imageListViewModel.getImageList("nature")
        imageListViewModel.imageList.observe(viewLifecycleOwner, Observer { resource ->
            when(resource) {
                is Resource.Loading -> {
                    showSnackBar("LOADING...")
                }
                is Resource.Success -> {
                    val rv = view.findViewById<RecyclerView>(R.id.imagesList_rv)
                    rv.also {
                        it.layoutManager = LinearLayoutManager(requireContext())
                        it.setHasFixedSize(true)
                        it.adapter = ImageListRvAdapter(resource.data!!, this)
                    }
                    if(resource.message != null) {
                        showSnackBar(resource.message)
                    }
                }
                is Resource.Error -> {
                    showSnackBar(resource.message)
                }
            }
        })
    }

    override fun onItemClick(item: ImageItem, position: Int) {
        showYesNoDialog(requireActivity(), "More",
        "Do you want to see more details about this picture?", isCancelable = false,
            yesCallback = {
                val action = ImageListF
                findNavController().navigate(R.id.imageDetailsFragment)
            }
        )
    }
}