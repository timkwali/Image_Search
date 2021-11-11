package com.timkwali.imagesearch.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.timkwali.imagesearch.R
import com.timkwali.imagesearch.databinding.ImageListItemBinding
import com.timkwali.imagesearch.domain.model.ImageItem
import com.timkwali.imagesearch.presentation.utils.ClickListener

class ImageListRvAdapter(
    private val imagesList: List<ImageItem>,
    private var listener: ClickListener<ImageItem>
): RecyclerView.Adapter<ImageListRvAdapter.ImageListViewHolder>() {

    inner class ImageListViewHolder(
        var imageListItemBinding: ImageListItemBinding
    ): RecyclerView.ViewHolder(imageListItemBinding.root) {
        fun setClick(imageItem: ImageItem, listener: ClickListener<ImageItem>) {
            imageListItemBinding.root.setOnClickListener {
                listener.onItemClick(imageItem, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ImageListViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context) ,
                R.layout.image_list_item,
                parent, false
            )
        )

    override fun onBindViewHolder(holder: ImageListViewHolder, position: Int) {
        val currentItem = imagesList[position]
        holder.imageListItemBinding.imageItem = currentItem
        holder.setClick(currentItem, listener)
    }

    override fun getItemCount(): Int = imagesList.size
}