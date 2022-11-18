package net.litoral.recipeapp

import retrofit2.http.GET
import retrofit2.Call

interface MealInterface {
    @GET("api/json/v1/1/random.php")
    fun getRandomMeal(): Call<MealList>
}