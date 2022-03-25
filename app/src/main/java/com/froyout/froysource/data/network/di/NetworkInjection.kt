package com.froyout.froysource.data.network.di

import com.froyout.froysource.data.network.CatFactService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkInjection {
    val retrofit = Retrofit.Builder()
            .baseUrl("https://catfact.ninja/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun provideCatFactService(): CatFactService{
        return retrofit.create(CatFactService::class.java)
    }
}