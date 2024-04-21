package com.example.xetaassignment

import retrofit2.Call
import retrofit2.http.GET

interface FoodApi {

    @GET("/food_info/")
    fun getFoodInfo(): Call<FoodResponse>

}