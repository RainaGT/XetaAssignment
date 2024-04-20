package com.example.xetaassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.bumptech.glide.Glide


class FoodActivity : AppCompatActivity() {

    private lateinit var image: ImageView
    private lateinit var name: TextView
    private lateinit var description: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SimilarItemAdapter

    // Define Retrofit service interface
    private val service: FoodApi by lazy {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://52.25.229.242:8000")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        retrofit.create(FoodApi::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

        findViews()

        fetchData()
    }

    private fun fetchData() {
        service.getFoodInfo().enqueue(object : Callback<FoodResponse> {
            override fun onResponse(call: Call<FoodResponse>, response: Response<FoodResponse>) {
                if (response.isSuccessful) {
                    val foodData = response.body()?.data
                    foodData?.let {

                        name.text = it.name
                        description.text = it.description
                        val imageUrl = it.image

                        Glide.with(this@FoodActivity)
                            .load(imageUrl)
                            .into(image)



                        // Update RecyclerView with similar items
                        setupRecyclerView(it.similar_items )
                    }
                } else {
                    // Handle error
                }
            }

            override fun onFailure(call: Call<FoodResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }

    private fun setupRecyclerView(similarItems: List<SimilarItem>) {
        adapter = SimilarItemAdapter(this, similarItems)
        recyclerView.adapter = adapter
    }

    private fun setRecyclerView(similarItems: List<SimilarItem>) {
        adapter = SimilarItemAdapter(this, similarItems)
        recyclerView.adapter = adapter
    }

    private fun findViews() {
        image = findViewById(R.id.main_img)
        name = findViewById(R.id.food_name)
        description = findViewById(R.id.food_desc)
        recyclerView = findViewById(R.id.recycler_meal_similar)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}




