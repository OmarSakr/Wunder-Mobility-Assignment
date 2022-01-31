package com.codevalley.wundermobilityassignment.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codevalley.wundermobilityassignment.model.carDetailsModel.CarDetailsModel
import com.codevalley.wundermobilityassignment.model.rentModel.RentModel
import com.codevalley.wundermobilityassignment.network.MainRepository
import com.codevalley.wundermobilityassignment.utils.Resource
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class CarDetailsViewModel @Inject constructor(
    private val mainRepository: MainRepository,
) : ViewModel() {
    private val _mutableStateFlowCarDetails: MutableStateFlow<Resource<Response<CarDetailsModel>>> =
        MutableStateFlow(
            Resource.loading(null)
        )
    val stateFlowCarDetails: StateFlow<Resource<Response<CarDetailsModel>>> =
        _mutableStateFlowCarDetails

    private val _mutableStateFlowQuickRental: MutableStateFlow<Resource<Response<RentModel>>> =
        MutableStateFlow(
            Resource.loading(null)
        )
    val stateFlowQuickRental: StateFlow<Resource<Response<RentModel>>> =
        _mutableStateFlowQuickRental

    fun carDetails(
        id: String
    ) {
        viewModelScope.launch(Dispatchers.IO)
        {
            _mutableStateFlowCarDetails.emit(Resource.loading(data = null))
            try {
                _mutableStateFlowCarDetails.emit(
                    Resource.success(
                        data = mainRepository.carDetails(id)
                    )
                )
            } catch (exception: Exception) {
                _mutableStateFlowCarDetails.emit(
                    Resource.error(
                        data = null,
                        message = exception.message ?: "Something went wrong.!"
                    )
                )
            }
        }
    }

    fun quickRental(
        authorization: String,
        url: String,
        jsonBody: JsonObject
    ) {
        viewModelScope.launch(Dispatchers.IO)
        {
            _mutableStateFlowQuickRental.emit(Resource.loading(data = null))
            try {
                _mutableStateFlowQuickRental.emit(
                    Resource.success(
                        data = mainRepository.quickRental(authorization,url,jsonBody)
                    )
                )
            } catch (exception: Exception) {
                _mutableStateFlowQuickRental.emit(
                    Resource.error(
                        data = null,
                        message = exception.message ?: "Something went wrong.!"
                    )
                )
            }
        }
    }

}