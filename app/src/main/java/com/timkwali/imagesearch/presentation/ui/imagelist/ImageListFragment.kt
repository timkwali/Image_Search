package com.timkwali.imagesearch.presentation.ui.imagelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.timkwali.imagesearch.R
import com.timkwali.imagesearch.common.Constants
import com.timkwali.imagesearch.common.Resource
import com.timkwali.imagesearch.databinding.FragmentImageListBinding
import com.timkwali.imagesearch.domain.model.ImageItem
import com.timkwali.imagesearch.presentation.adapter.ImageListRvAdapter
import com.timkwali.imagesearch.presentation.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageListFragment : Fragment(), ClickListener<ImageItem> {

    private lateinit var binding: FragmentImageListBinding
    private val imageListViewModel: ImageListViewModel by activityViewModels()
    private var rvContainsData = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_image_list, container, false)
        binding.apply {
            viewModel = imageListViewModel
            clickHandlers = ClickHandlers
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.swipeToRefresh.setColorSchemeResources(R.color.dark_orange)
        rvContainsData = savedInstanceState?.getBoolean(Constants.RV_CONTAINS_DATA) ?: false
        imageListViewModel.imageList.observe(viewLifecycleOwner, Observer { resource ->
            binding.apply {
                swipeToRefresh.isRefreshing = resource is Resource.Loading

                noNetwork.isVisible = !rvContainsData && resource is Resource.Error
                if(!resource.message.isNullOrEmpty()) showSnackBar(resource.message)
//                if(resource is Resource.Success && resource.message != null) showSnackBar(resource.message)
//                if(resource is Resource.Error && resource.data.isNullOrEmpty()) showSnackBar(resource.message)

                if(!resource.data.isNullOrEmpty() && resource.data[0].searchQuery == imageListViewModel.currentSearchQuery) {
                    imagesListRv.also {
                        it.layoutManager = LinearLayoutManager(requireContext())
                        it.setHasFixedSize(true)
                        it.adapter = ImageListRvAdapter(resource.data, this@ImageListFragment)
                    }
                    rvContainsData = true
                }
            }
        })
    }

    override fun onItemClick(item: ImageItem, position: Int) {
        showYesNoDialog(requireActivity(), "More",
        "Do you want to see more details about this picture?", isCancelable = false,
            yesCallback = {
                val action = ImageListFragmentDirections.actionImageListFragmentToImageDetailsFragment(item)
                findNavController().navigate(action)
            }
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(Constants.RV_CONTAINS_DATA, rvContainsData)
    }
}