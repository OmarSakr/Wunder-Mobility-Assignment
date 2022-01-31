package com.codevalley.wundermobilityassignment.network

import com.codevalley.wundermobilityassignment.model.carDetailsModel.CarDetailsModel
import com.codevalley.wundermobilityassignment.model.carsModel.CarsModel
import com.codevalley.wundermobilityassignment.model.rentModel.RentModel
import com.google.gson.JsonObject
import retrofit2.Response
import javax.inject.Inject

class ApiHelper @Inject constructor(private val apiService: ApiService) {

    suspend fun cars(

    ): Response<CarsModel> = apiService.cars()

    suspend fun carDetails(
        id: String
    ): Response<CarDetailsModel> = apiService.carDetails(id)

    suspend fun quickRental(
         authorization: String,
         url: String,
         jsonBody: JsonObject

    ): Response<RentModel> = apiService.quickRental(authorization,url,jsonBody)

}