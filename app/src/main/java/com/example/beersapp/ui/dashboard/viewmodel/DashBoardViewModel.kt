package com.example.beersapp.ui.dashboard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beersapp.data.network.model.PunkResponse
import com.example.beersapp.domain.common.BaseResult
import com.example.beersapp.domain.common.Status
import com.example.beersapp.domain.common.StatusData
import com.example.beersapp.domain.usecase.GetBeersListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class DashBoardViewModel @Inject constructor(private val getBeersListUseCase: GetBeersListUseCase) :
    ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + SupervisorJob()

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancel()
    }

    private val _mutableStateList =
        MutableStateFlow<StatusData<List<PunkResponse>>>(StatusData(responseType = Status.LOADING))
    val stateList: StateFlow<StatusData<List<PunkResponse>>> = _mutableStateList.asStateFlow()

    fun search(query: String, page: Int, perPage: Int) {
        viewModelScope.launch {
            _mutableStateList.value = StatusData(responseType = Status.LOADING)
            val result = withContext(Dispatchers.IO) {
                getBeersListUseCase.invoke(page, perPage)
            }
            when (result) {
                is BaseResult.Error -> {
                    _mutableStateList.value =
                        StatusData(responseType = Status.ERROR, error = result.exception)
                }

                is BaseResult.Success -> {
                    val filteredResults = result.data?.filter { beer ->
                        beer.name.contains(query, ignoreCase = true)
                    }
                    _mutableStateList.value =
                        StatusData(responseType = Status.SUCCESS, data = filteredResults)
                }
            }
        }
    }

}
