package com.timkwali.imagesearch.presentation.utils

import android.content.res.AssetManager
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.timkwali.imagesearch.R
import com.timkwali.imagesearch.domain.model.ImageItem
import com.timkwali.imagesearch.presentation.adapter.ImageListRvAdapter

@BindingAdapter("image")
fun loadImage(view: ImageView, url: String) {
    Glide.with(view)
        .load(url)
        .into(view)
}

@BindingAdapter("chipGroup")
fun setUpChipGroup(chipGroup: ChipGroup, tags: String) {
    chipGroup.removeAllViews()
    val tagsList = tags.split(", ")
    for(item in tagsList) {
        val newChip = Chip(chipGroup.context)
        newChip.apply {
            text = item.trim()
            setTextAppearance(R.style.chipText)
        }
        chipGroup.addView(newChip)
    }
}

object ClickHandlers {
    fun navigateBack(view: View) {
        view.findNavController().popBackStack()
    }
}


