package com.froyout.froysource.base

import com.froyout.froysource.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

object BaseCall: BaseMapping(){
    @Suppress("UNCHECKED_CAST")
    fun <T, V, Y: ErrorResponse> call(classes: Class<Y>, dispatcher: CoroutineDispatcher = Dispatchers.IO, apiCall: suspend() -> T): Flow<Resource<V>> {
        lateinit var response: Response<T>
        return flow{
            emit(Resource.Loading())
            try {
                apiCall.invoke()
                    response = apiCall.invoke() as Response<T>
                val data = response.body()

                if (!response.isSuccessful) {
                    val errorResponse = convertErrorBody(
                        responseBody = response.errorBody(),
                        classes = classes
                    )
                    emit(Resource.Error(errorResponse?.message, errorResponse?.code))
                }else{
                    if(response.body() is BaseResponse<*>)
                        emit(Resource.Success((data as BaseResponse<V>).results))
                    else
                        emit(Resource.Success(data as V))
                }
            } catch (throwable: Throwable) {
                val error = convertErrorBody(throwable, classes = classes)
                emit(Resource.Error(error?.message, error?.code))
            }
        }.flowOn(dispatcher)
    }
}