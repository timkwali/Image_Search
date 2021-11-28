package com.timkwali.imagesearch.presentation.utils

import android.content.res.ColorStateList
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.timkwali.imagesearch.R
import com.timkwali.imagesearch.presentation.ui.imagelist.ImageListViewModel

@BindingAdapter("image")
fun loadImage(view: ImageView, url: String?) {
    if(url != null) {
        Glide.with(view)
            .load(url)
            .placeholder(R.drawable.ic_round_camera)
            .error(R.drawable.pixabay_logo)
            .into(view)
    }
}

@BindingAdapter("chipGroup")
fun setUpChipGroup(chipGroup: ChipGroup, tags: String?) {
    if(!tags.isNullOrEmpty()) {
        chipGroup.removeAllViews()
        val tagsList = tags.split(", ")
        for(item in tagsList) {
            val newChip = Chip(chipGroup.context)
            newChip.apply {
                newChip.chipStrokeWidth = 1F
                newChip.chipStrokeColor = ColorStateList.valueOf(resources.getColor(R.color.dark_orange))
                text = item.trim()
                setTextAppearance(R.style.chipText)
            }
            chipGroup.addView(newChip)
        }
    }
}

@BindingAdapter("setSwipeToRefresh")
fun setSwipeToRefresh(swipeRefreshLayout: SwipeRefreshLayout, viewModel: ImageListViewModel) {
    swipeRefreshLayout.setOnRefreshListener {
        viewModel.getImageList(viewModel.currentSearchQuery)
    }
}

@BindingAdapter("searchQuery")
fun searchQuery(searchView: SearchView, viewmodel: ImageListViewModel) {
    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            viewmodel.getImageList(query.toString())
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return false
        }
    })
}

@BindingAdapter("retry")
fun retry(button: Button, viewModel: ImageListViewModel) {
    button.setOnClickListener {
        viewModel.getImageList(viewModel.currentSearchQuery)
    }
}


object ClickHandlers {
    fun navigateBack(view: View) {
        view.findNavController().popBackStack()
    }
}


