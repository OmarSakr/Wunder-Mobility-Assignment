package com.codevalley.wundermobilityassignment.network

import com.codevalley.wundermobilityassignment.model.carDetailsModel.CarDetailsModel
import com.codevalley.wundermobilityassignment.model.carsModel.CarsModel
import com.codevalley.wundermobilityassignment.model.rentModel.RentModel
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @POST
    suspend fun quickRental(
        @Header("Authorization") authorization: String,
        @Url url: String,
        @Body jsonBody: JsonObject
    ): Response<RentModel>

    @GET(AppUrls.cars)
    suspend fun cars(
    ): Response<CarsModel>


    @GET(AppUrls.carDetails)
    suspend fun carDetails(
        @Path("id") id: String
    ): Response<CarDetailsModel>
}