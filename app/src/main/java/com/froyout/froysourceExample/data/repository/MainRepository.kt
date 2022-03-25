package com.froyout.froysourceExample.data.repository

import com.froyout.froysourceExample.Resource
import com.froyout.froysourceExample.base.BaseCall
import com.froyout.froysourceExample.data.model.CatErrorResponse
import com.froyout.froysourceExample.data.model.CatFact
import com.froyout.froysourceExample.data.network.di.NetworkInjection
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