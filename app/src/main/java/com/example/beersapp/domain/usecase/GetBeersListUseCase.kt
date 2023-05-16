package com.example.beersapp.domain.usecase

import com.example.beersapp.data.network.model.PunkResponse
import com.example.beersapp.data.repository.ApiRepository
import com.example.beersapp.domain.common.BaseResult
import javax.inject.Inject

class GetBeersListUseCase @Inject constructor(private val repository: ApiRepository) {
    suspend operator fun invoke(
        page: Int,
        perPage: Int
    ): BaseResult<List<PunkResponse>> {
        return repository.getBeersList(page, perPage)
    }

}