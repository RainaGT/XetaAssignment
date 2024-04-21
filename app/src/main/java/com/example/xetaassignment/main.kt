package com.example.xetaassignment

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun main() {
    val retrofit = Retrofit.Builder()
        .baseUrl(" http://52.25.229.242:8000/food_info/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(FoodApi::class.java)

    val call = service.getFoodInfo()

    call.enqueue(object : Callback<FoodResponse> {
        override fun onResponse(call: Call<FoodResponse>, response: Response<FoodResponse>) {
            if (response.isSuccessful) {
                val foodInfoResponse = response.body()
                // Handle the response here
                println("Success: ${foodInfoResponse?.success}")
                println("Message: ${foodInfoResponse?.message}")
                println("data: ${foodInfoResponse?.data}")

                // Access other properties as needed
            } else {
                // Handle error
                println("Error: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<FoodResponse>, t: Throwable) {
            // Handle failure
            println("Failed to make API call: ${t.message}")
        }
    })
}