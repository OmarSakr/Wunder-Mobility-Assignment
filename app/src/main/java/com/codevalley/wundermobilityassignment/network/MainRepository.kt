package com.codevalley.wundermobilityassignment.network

import com.codevalley.wundermobilityassignment.model.carDetailsModel.CarDetailsModel
import com.codevalley.wundermobilityassignment.model.carsModel.CarsModel
import com.codevalley.wundermobilityassignment.model.rentModel.RentModel
import com.google.gson.JsonObject
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun cars(

    ): Response<CarsModel> = apiHelper.cars()

    suspend fun carDetails(
        id: String
    ): Response<CarDetailsModel> = apiHelper.carDetails(id)

    suspend fun quickRental(
        authorization: String,
        url: String,
        jsonBody: JsonObject
    ): Response<RentModel> = apiHelper.quickRental(authorization, url,jsonBody)

}