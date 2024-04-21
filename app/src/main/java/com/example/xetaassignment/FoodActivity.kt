package com.example.xetaassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.xetaassignment.dash.Today
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FoodActivity : AppCompatActivity() {

    private lateinit var imagetext: ImageView
    private lateinit var nametext: TextView
    private lateinit var descriptiontext: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SimilarItemAdapter
    private lateinit var genericFacts: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)


        val back = findViewById<TextView>(R.id.back)
        back.setOnClickListener {
            // Start the new activity here
            val intent = Intent(this, Today::class.java)
            startActivity(intent)
        }

        findViews()

        // Initialize Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("http://52.25.229.242:8000/food_info/") // base URL excluding endpoint
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(FoodApi::class.java)

        val call = service.getFoodInfo()

        call.enqueue(object : Callback<FoodResponse> {
            override fun onResponse(call: Call<FoodResponse>, response: Response<FoodResponse>) {
                if (response.isSuccessful) {
                    val foodInfoResponse = response.body()
                    // Populate views with fetched data
                    populateViews(foodInfoResponse?.data)
                    foodInfoResponse?.data?.generic_facts?.let {
                        genericFacts = it
                        // Update RecyclerView with the actual data
                        updateRecyclerView()
                    }
                    populateNutritionInfo(foodInfoResponse?.data)
                } else {
                    //Handle error
                    println("Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<FoodResponse>, t: Throwable) {
                // Handle failure
                println("Failed to make API call: ${t.message}")
            }
        })


    }

    private fun findViews() {
        imagetext = findViewById(R.id.main_img)
        nametext = findViewById(R.id.food_name)
        descriptiontext = findViewById(R.id.food_desc)
        recyclerView = findViewById(R.id.recycler_meal_similar)
    }

    private fun populateViews(foodData: FoodResponseData?) {
        nametext.text = foodData?.name
        descriptiontext.text = foodData?.description
        Picasso.get().load(foodData?.image).into(imagetext)


        // Set up RecyclerView with similar items if available
        if (!foodData?.similar_items.isNullOrEmpty()) {
            adapter = SimilarItemAdapter(foodData?.similar_items)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = adapter
        }


    }

    private fun updateRecyclerView() {
        val genericFactList = genericFacts.map { GenericFact(it) }

        // Set up RecyclerView with generic facts
        val recyclerView: RecyclerView = findViewById(R.id.recycler_fact)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = GenericFactAdapter(genericFactList)
    }

    private fun populateNutritionInfo(foodData: FoodResponseData?) {

        val nutritionInfo = foodData?.nutrition_info_scaled ?: emptyList()

        val textEnergyPerSer = findViewById<TextView>(R.id.eng_per_ser)
        val textEnergyPerGm = findViewById<TextView>(R.id.eng_per_gm)
        val textProteinPerSer = findViewById<TextView>(R.id.pro_per_ser)
        val textProteinPerGm = findViewById<TextView>(R.id.pro_per_gm)
        val textTransPerSer = findViewById<TextView>(R.id.trans_per_ser)
        val textTransPerGm = findViewById<TextView>(R.id.trans_per_gm)
        val textSatuPerSer = findViewById<TextView>(R.id.satu_per_ser)
        val textSatuPerGm = findViewById<TextView>(R.id.satu_per_gm)
        val textCarbPerSer = findViewById<TextView>(R.id.carb_per_ser)
        val textCarbPerGm = findViewById<TextView>(R.id.carb_per_gm)
        val textFiberPerSer = findViewById<TextView>(R.id.fiber_per_ser)
        val textFiberPerGm = findViewById<TextView>(R.id.fiber_per_gm)


        val textPer250gm = findViewById<TextView>(R.id.text1)
        val textPerServe = findViewById<TextView>(R.id.text2)


        textPer250gm.text = "Per 250gm"
        textPerServe.text = "Per Serve"


        for (nutrient in nutritionInfo) {
            when (nutrient.name) {
                "Energy" -> {
                    textEnergyPerSer.text = "${nutrient.value} ${nutrient.units}"
                    textEnergyPerGm.text = "${nutrient.value / 250} ${nutrient.units}"
                }
                "Protein" -> {
                    textProteinPerSer.text = "${nutrient.value} ${nutrient.units}"
                    textProteinPerGm.text = "${nutrient.value / 250} ${nutrient.units}"
                }
                "Trans Fat" -> {
                    textTransPerSer.text = "${nutrient.value} ${nutrient.units}"
                    textTransPerGm.text = "${nutrient.value / 250} ${nutrient.units}"
                }
                "Saturated Fat" -> {
                    textSatuPerSer.text = "${nutrient.value} ${nutrient.units}"
                    textSatuPerGm.text = "${nutrient.value / 250} ${nutrient.units}"
                }
                "Carbohydrates" -> {
                    textCarbPerSer.text = "${nutrient.value} ${nutrient.units}"
                    textCarbPerGm.text = "${nutrient.value / 250} ${nutrient.units}"
                }
                "Fiber" -> {
                    textFiberPerSer.text = "${nutrient.value} ${nutrient.units}"
                    textFiberPerGm.text = "${nutrient.value / 250} ${nutrient.units}"
                }

            }
        }
    }

    }







//package com.example.xetaassignment
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//
//class FoodActivity : AppCompatActivity() {
//
//    private lateinit var imagetext: ImageView
//    private lateinit var nametext: TextView
//    private lateinit var descriptiontext: TextView
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var adapter: SimilarItemAdapter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_food)
//
//        findViews()
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl("http://52.25.229.242:8000/") // base URL excluding endpoint
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        val service = retrofit.create(FoodApi::class.java)
//
//        val call = service.getFoodInfo()
//
//
//        call.enqueue(object : Callback<FoodResponse> {
//            override fun onResponse(call: Call<FoodResponse>, response: Response<FoodResponse>) {
//                if (response.isSuccessful) {
//                    val foodInfoResponse = response.body()
//
//
//                    val intent = Intent(this@FoodActivity, FoodActivity::class.java).apply {
//                        putExtra("name", foodInfoResponse?.data?.name)
//                        putExtra("description", foodInfoResponse?.data?.description)
//                        putExtra("imageUrl", foodInfoResponse?.data?.image)
//
//                        // Assuming similarItems is a list of SimilarItem objects
//                        putExtra("similarItems", foodInfoResponse?.data?.similar_items?.toTypedArray())
//                    }
//                    startActivity(intent)
//                } else {
//                    // Handle error
//                    println("Error: ${response.code()}")
//                }
//            }
//
//            override fun onFailure(call: Call<FoodResponse>, t: Throwable) {
//                // Handle failure
//                println("Failed to make API call: ${t.message}")
//            }
//        })
//
//
////        val foodName = intent.getStringExtra("name")
////        val foodDescription = intent.getStringExtra("description")
////        val imageUrl = intent.getStringExtra("imageUrl")
////
////        nametext.text = foodName
////        descriptiontext.text = foodDescription
////        Glide.with(this).load(imageUrl).into(imagetext)
//
//
//    }
//
//    private fun findViews() {
//        imagetext = findViewById(R.id.main_img)
//        nametext = findViewById(R.id.food_name)
//        descriptiontext = findViewById(R.id.food_desc)
//        recyclerView = findViewById(R.id.recycler_meal_similar)
//    }
//}