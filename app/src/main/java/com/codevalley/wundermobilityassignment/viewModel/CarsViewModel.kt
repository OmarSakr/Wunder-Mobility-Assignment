package com.codevalley.wundermobilityassignment.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codevalley.wundermobilityassignment.model.carsModel.CarsModel
import com.codevalley.wundermobilityassignment.network.MainRepository
import com.codevalley.wundermobilityassignment.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CarsViewModel @Inject constructor(
    private val mainRepository: MainRepository,
) : ViewModel() {

    private val _mutableStateFlowCars: MutableStateFlow<Resource<Response<CarsModel>>> =
        MutableStateFlow(
            Resource.loading(null)
        )
    val stateFlowCars: StateFlow<Resource<Response<CarsModel>>> =
        _mutableStateFlowCars

    fun cars(
    ) {
        viewModelScope.launch(Dispatchers.IO)
        {
            _mutableStateFlowCars.emit(Resource.loading(data = null))
            try {
                _mutableStateFlowCars.emit(
                    Resource.success(
                        data = mainRepository.cars()
                    )
                )
            } catch (exception: Exception) {
                _mutableStateFlowCars.emit(
                    Resource.error(
                        data = null,
                        message = exception.message ?: "Something went wrong.!"
                    )
                )
            }
        }
    }


}