package net.litoral.recipeapp

import retrofit2.Retrofit


class RetrofitHelper {
val baseUrl= ""

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl).build()
    }
}