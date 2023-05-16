package com.example.beersapp.domain

import com.example.beersapp.data.network.model.PunkResponse
import com.example.beersapp.domain.common.BaseResult

interface IApiRepository {
    suspend fun getBeersList(
        page: Int,
        perPage: Int
    ): BaseResult<List<PunkResponse>>
}