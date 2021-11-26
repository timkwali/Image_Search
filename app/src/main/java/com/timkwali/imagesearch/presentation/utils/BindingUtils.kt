package com.timkwali.imagesearch.presentation.utils

import android.content.res.ColorStateList
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.timkwali.imagesearch.R

@BindingAdapter("image")
fun loadImage(view: ImageView, url: String?) {
    if(url != null) {
        Glide.with(view)
            .load(url)
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


object ClickHandlers {
    fun navigateBack(view: View) {
        view.findNavController().popBackStack()
    }

}


