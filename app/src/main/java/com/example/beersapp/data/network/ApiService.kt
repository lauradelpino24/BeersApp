package com.example.beersapp.data.network

import com.example.beersapp.data.network.model.PunkResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("beers")
    suspend fun getBeersList(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<List<PunkResponse>>
}