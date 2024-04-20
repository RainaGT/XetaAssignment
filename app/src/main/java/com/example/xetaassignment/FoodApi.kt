package com.example.xetaassignment

import retrofit2.http.GET

interface FoodApi {

    @GET("/food_info/")
    suspend fun getFoodDetails(): FoodResponse



}