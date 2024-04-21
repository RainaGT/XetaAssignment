package com.example.xetaassignment


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso


class SimilarItemAdapter(private val similarItems: List<SimilarItem>?) :
    RecyclerView.Adapter<SimilarItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_similar_recipe, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = similarItems?.get(position)
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return similarItems?.size ?: 0
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image_similar)
          val textView: TextView = itemView.findViewById(R.id.text_similar_title)

        fun bind(similarItem: SimilarItem?) {
            similarItem?.let {
                textView.text = it.name
                Picasso.get().load(it.image).into(imageView)

            }
        }
    }
}
//class SimilarItemAdapter(private val context: Context, private val similarItems: List<SimilarItem>) :
//    RecyclerView.Adapter<SimilarItemAdapter.SimilarItemViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarItemViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_similar_recipe, parent, false)
//        return SimilarItemViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: SimilarItemViewHolder, position: Int) {
//        val currentItem = similarItems[position]
//
//        // Load image using Glide
//        Glide.with(context)
//            .load(currentItem.image)
//            .into(holder.imageView)
//
//        holder.textView.text = currentItem.name
//    }
//
//    override fun getItemCount(): Int {
//        return similarItems.size
//    }
//
//    inner class SimilarItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val imageView: ImageView = itemView.findViewById(R.id.image_similar)
//        val textView: TextView = itemView.findViewById(R.id.text_similar_title)
//    }
//}