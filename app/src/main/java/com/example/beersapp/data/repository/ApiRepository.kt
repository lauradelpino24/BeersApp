package com.example.beersapp.data.repository

import com.example.beersapp.data.network.ApiService
import com.example.beersapp.data.network.model.PunkResponse
import com.example.beersapp.domain.IApiRepository
import com.example.beersapp.domain.common.BaseResult
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService): IApiRepository {

    override suspend fun getBeersList(page: Int, perPage: Int): BaseResult<List<PunkResponse>> {
        val response = apiService.getBeersList(page, perPage)
        return if (response.isSuccessful) {
            BaseResult.Success(response.body())
        } else {
            return BaseResult.Error(Exception(response.message()))
        }
    }

}