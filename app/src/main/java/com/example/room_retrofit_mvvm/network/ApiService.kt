package com.example.retrofitroom.network

import com.example.retrofitroom.database.model.DataResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/api/character")
    suspend fun getListCharacter(@Query("page") page:Int) : Response<DataResult>
}