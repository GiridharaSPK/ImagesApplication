package com.giridharaspk.imagesapplication.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.giridharaspk.imagesapplication.R
import com.giridharaspk.imagesapplication.data.model.ImageDetails
import com.giridharaspk.imagesapplication.databinding.ItemImageDetailsBinding

class ImagesAdapter(private val context: Context) :
    RecyclerView.Adapter<ImagesAdapter.ImageDetailsViewHolder>() {

    private lateinit var list: ArrayList<ImageDetails>

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

    private fun loadImage(imageView: ImageView, imageReference: String?) {
        Glide.with(context)
            .load(imageReference)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .skipMemoryCache(false)
            .transition(DrawableTransitionOptions.withCrossFade())
//            .placeholder(R.drawable.ic_launcher_foreground)
//            .error(R.drawable.ic_launcher_background)
            .into(imageView)
    }

    override fun getItemCount() = list.size

    fun setImagesList(imagesList: ArrayList<ImageDetails>) {
        list = imagesList
        notifyDataSetChanged()
    }

}