package com.timkwali.imagesearch.presentation.utils

interface ClickListener<T> {
    fun onItemClick(item: T, position: Int)
}