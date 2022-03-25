package com.froyout.froysource.data.repository

import com.froyout.froysource.Resource
import com.froyout.froysource.base.BaseCall
import com.froyout.froysource.base.ErrorResponse
import com.froyout.froysource.data.model.CatErrorResponse
import com.froyout.froysource.data.model.CatFact
import com.froyout.froysource.data.network.di.NetworkInjection
import kotlinx.coroutines.flow.Flow

interface IMainRepository{
    suspend fun getCatFacts(): Flow<Resource<List<CatFact>>>
}

class MainRepository: IMainRepository {
    override suspend fun getCatFacts(): Flow<Resource<List<CatFact>>> {
        return BaseCall.call(CatErrorResponse::class.java){
            NetworkInjection.provideCatFactService().getCatFacts()
        }
    }

}