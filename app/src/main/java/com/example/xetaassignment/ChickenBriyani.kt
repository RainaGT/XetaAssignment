package com.example.xetaassignment

data class ChickenBriyani (
    val name: String,
    val cuisine: String,
    val description: String,
    val nutritionInfo: List<NutritionInfo>,
    val generic_facts: List<String>,
    val image: String,
    val similar_items: List<SimilarItem>,
    val servings: Double

)

