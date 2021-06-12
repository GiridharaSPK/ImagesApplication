package com.giridharaspk.imagesapplication.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.giridharaspk.imagesapplication.R
import com.giridharaspk.imagesapplication.data.model.ImageDetails
import com.giridharaspk.imagesapplication.databinding.ItemImageDetailsBinding

class ImagesAdapter : RecyclerView.Adapter<ImagesAdapter.ImageDetailsViewHolder>() {

    lateinit var list: ArrayList<ImageDetails>

    class ImageDetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: ItemImageDetailsBinding = ItemImageDetailsBinding.bind(itemView);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageDetailsViewHolder {
        return ImageDetailsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_image_details,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ImageDetailsViewHolder, position: Int) {
        val imageItem = list[position]
        holder.binding.llImage.visibility = View.VISIBLE
        holder.binding.apply {
            tvTitle.text = imageItem.title ?: ""
            tvDescription.text = imageItem.description ?: ""
            loadImage(ivImage, imageItem.imageHref)
        }
    }

    private fun loadImage(ivImage: ImageView, imageHref: String?) {
        TODO("Not yet implemented")
    }

    override fun getItemCount() = list.size

    fun setImagesList(imagesList: ArrayList<ImageDetails>) {
        list = imagesList
        notifyDataSetChanged()
    }

}