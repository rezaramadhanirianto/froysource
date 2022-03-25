package com.froyout.froysourceExample.data.network

import com.froyout.froysourceExample.data.model.CatFact
import com.froyout.froysourceExample.data.model.Responses
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CatFactService {
    @GET("factsasdfasdf")
    suspend fun getCatFacts(
        @Query("limit") limit: Int = 10
    ): Response<Responses<List<CatFact>>>
}