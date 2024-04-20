package com.example.xetaassignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class SimilarRecipeAdapter( val similarRecipes: List<SimilarItem>) :
    RecyclerView.Adapter<SimilarRecipeAdapter.SimilarRecipeViewHolder>() {

    class SimilarRecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recipeName: TextView = itemView.findViewById(R.id.text_similar_title)
        val recipeImage: ImageView = itemView.findViewById(R.id.image_similar)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarRecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_similar_recipe, parent, false)
        return SimilarRecipeViewHolder(view)
    }




    override fun onBindViewHolder(holder: SimilarRecipeViewHolder, position: Int) {
        val recipe = similarRecipes[position]
        holder.recipeName.text = recipe.name

        Glide.with(holder.itemView)
            .load(recipe.image)
            .apply(RequestOptions().centerCrop())
            .into(holder.recipeImage)

    }

    override fun getItemCount(): Int = similarRecipes.size
}