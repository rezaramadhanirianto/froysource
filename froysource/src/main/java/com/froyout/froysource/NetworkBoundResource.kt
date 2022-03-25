package com.froyout.froysourceExample

import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType>{
    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        if(!noNeedLocalSource()) {
            val databaseSource = loadFromDB()?.first()
            databaseSource?.let {
                emit(Resource.Success(it))

                if (shouldFetch(it)) {
                    createCall().collect {
                        when (val resources = it) {
                            is Resource.Success -> {
                                resources.data?.let { saveCallResult(it) }
                                loadFromDB()?.let{
                                    emitAll(it.map { Resource.Success(it) })
                                }
                            }
                            is Resource.Error -> {
                                onFetchFailed()
                                emit(resources)
                            }
                        }
                    }
                }
            }
        }else{
            createCall().collect {
                when (val resources = it) {
                    is Resource.Success -> {
                        emit(
                            Resource.Success(
                                resources.data?.let {
                                    mapToResultType(it)
                                }
                            )
                        )
                    }
                    is Resource.Error -> {
                        onFetchFailed()
                        emit(resources)
                    }
                }
            }
        }
    }

    protected open fun onFetchFailed() {}

    protected open fun loadFromDB(): Flow<ResultType>? {
        return null
    }

    protected open fun shouldFetch(data: ResultType): Boolean {
        return false
    }

    protected abstract suspend fun createCall(): Flow<Resource<RequestType>>

    protected open suspend fun saveCallResult(data: RequestType) {  }

    protected open fun noNeedLocalSource(): Boolean{
        return true
    }

    protected abstract fun mapToResultType(data: RequestType): ResultType?

    fun asFlow(): Flow<Resource<ResultType>> = result
}