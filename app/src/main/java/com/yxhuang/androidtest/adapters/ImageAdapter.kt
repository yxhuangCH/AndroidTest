package com.yxhuang.androidtest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.yxhuang.androidtest.R
import javax.inject.Inject

/**
 * Created by yxhuang
 * Date: 2022/5/8
 * Description:
 */
class ImageAdapter @Inject constructor(
    private val glide: RequestManager
): RecyclerView.Adapter<ImageAdapter.ImageViewHolder>(){



    class ImageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val ivShoppingImage: ImageView = itemView.findViewById<ImageView>(R.id.ivShoppingImage)
    }

    private val diffCallback = object : DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var images : List<String>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_image,
                parent,
                false
            )
        )
    }

    private var onItemClickListener:((String) -> Unit)? = null

    fun setOnItemClickListener(listener: (String) -> Unit){
        onItemClickListener = listener
    }


    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val url = images[position]
        holder.itemView.let {
            glide.load(url).into(holder.ivShoppingImage)
            setOnItemClickListener {
                onItemClickListener?.let { click ->
                    click(url)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }
}