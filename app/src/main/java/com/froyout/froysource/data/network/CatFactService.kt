package com.froyout.froysource.data.network

import com.froyout.froysource.data.model.CatFact
import com.froyout.froysource.data.model.Responses
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CatFactService {
    @GET("factsasdfasdf")
    suspend fun getCatFacts(
        @Query("limit") limit: Int = 10
    ): Response<Responses<List<CatFact>>>
}