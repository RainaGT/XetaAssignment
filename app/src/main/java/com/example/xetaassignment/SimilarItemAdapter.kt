package com.example.xetaassignment


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class SimilarItemAdapter(private val context: Context, private val similarItems: List<SimilarItem>) :
    RecyclerView.Adapter<SimilarItemAdapter.SimilarItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_similar_recipe, parent, false)
        return SimilarItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: SimilarItemViewHolder, position: Int) {
        val currentItem = similarItems[position]

        // Load image using Glide
        Glide.with(context)
            .load(currentItem.image)
            .into(holder.imageView)

        holder.textView.text = currentItem.name
    }

    override fun getItemCount(): Int {
        return similarItems.size
    }

    inner class SimilarItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image_similar)
        val textView: TextView = itemView.findViewById(R.id.text_similar_title)
    }
}