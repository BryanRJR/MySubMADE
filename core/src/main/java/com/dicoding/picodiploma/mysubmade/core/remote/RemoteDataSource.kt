package com.dicoding.picodiploma.mysubmade.core.remote

import android.util.Log
import com.dicoding.picodiploma.mysubmade.core.remotenet.ApiResponse
import com.dicoding.picodiploma.mysubmade.core.remotenet.ApiService
import com.dicoding.picodiploma.mysubmade.core.remoteres.MovieRes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    companion object {
        const val TAG = "Remote Data Source"
    }

    suspend fun getListMovie(): Flow<ApiResponse<List<MovieRes>>> {
        return flow {
            try {
                val response = apiService.getMovies()
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}