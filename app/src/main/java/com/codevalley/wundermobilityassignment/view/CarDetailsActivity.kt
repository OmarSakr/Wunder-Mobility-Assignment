package com.codevalley.wundermobilityassignment.view

import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.codevalley.wundermobilityassignment.R
import com.codevalley.wundermobilityassignment.databinding.ActivityCarDetailsBinding
import com.codevalley.wundermobilityassignment.network.AppUrls
import com.codevalley.wundermobilityassignment.utils.BaseActivity
import com.codevalley.wundermobilityassignment.utils.Constants
import com.codevalley.wundermobilityassignment.utils.Helper
import com.codevalley.wundermobilityassignment.utils.Status
import com.codevalley.wundermobilityassignment.viewModel.CarDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import com.google.gson.JsonObject
import java.lang.Exception


@AndroidEntryPoint
class CarDetailsActivity : BaseActivity<ActivityCarDetailsBinding>() {
    private var carId = ""
    private val carDetailsViewModel: CarDetailsViewModel by viewModels()

    override fun setUpViews() {
        initUi()
        initEventDriven()
    }

    private fun initEventDriven() {
        binding.tvRent.setOnClickListener {

            carDetailsViewModel.quickRental(
                Constants.beforeApiToken + Constants.token,
                AppUrls.quickRental, createRequestBody(carId)
            )
            getQuickRental()

        }
        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun getQuickRental() {
        lifecycleScope.launchWhenStarted {
            carDetailsViewModel.stateFlowQuickRental.collect {
                when (it.status) {
                    Status.LOADING -> {
                        Log.e("QuickRentalLOADING", it.message.toString())
                    }
                    Status.SUCCESS -> {
                        if (it.data?.code().toString() == "200") {
                            val response = it.data?.body()
                            if (response != null) {
                                Toast.makeText(
                                    this@CarDetailsActivity,
                                    getString(R.string.rentedSuccessfully),
                                    Toast.LENGTH_LONG
                                ).show()

                                finish()
                            }
                        } else {
                            Toast.makeText(
                                this@CarDetailsActivity,
                                getString(R.string.somethingWrongWithThisCar),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                    Status.ERROR -> {
                        Log.e("QuickRentalError", it.message.toString())
                    }
                }

            }
        }


    }

    override fun getViewBinding() = ActivityCarDetailsBinding.inflate(layoutInflater)

    private fun initUi() {
        carId = intent.getStringExtra("carId").toString()
        Log.e("carId1", carId)
        carDetailsViewModel.carDetails(carId)
        getCarDetails()
    }

    private fun getCarDetails() {
        lifecycleScope.launchWhenStarted {
            carDetailsViewModel.stateFlowCarDetails.collect {
                when (it.status) {
                    Status.LOADING -> {
                        Log.e("CarDetailsLOADING", it.message.toString())
                    }
                    Status.SUCCESS -> {
                        if (it.data?.code().toString() == "200") {
                            val response = it.data?.body()
                            if (response != null) {
                                Helper.LoadImageWithPicasso(
                                    response.vehicleTypeImageUrl,
                                    this@CarDetailsActivity,
                                    binding.ivCarImage
                                )
                                if (response.title != "") {
                                    binding.tvTitle.text = response.title
                                } else {
                                    binding.tvTitle.text = getString(R.string.noTitle)
                                }
                                binding.tvIsClean.text = response.isClean.toString()
                                binding.tvIsDamaged.text = response.isDamaged.toString()
                                binding.tvLicencePlate.text = response.licencePlate
                                binding.tvFuelLevel.text = response.fuelLevel.toString()
                                binding.tvPricingTime.text = response.pricingTime
                                binding.tvPricingParking.text = response.pricingParking
                                binding.tvAddress.text = response.address
                                binding.tvZipCode.text = response.zipCode
                                binding.tvCity.text = response.city
                                binding.tvLat.text = response.lat.toString()
                                binding.tvLon.text = response.lon.toString()
                                binding.tvDamageDescription.text = response.damageDescription
                            }
                        } else {
                            Toast.makeText(
                                this@CarDetailsActivity,
                                getString(R.string.somethingWrongWithThisCar),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                    Status.ERROR -> {
                        Log.e("CarDetailsError", it.message.toString())
                    }
                }

            }
        }

    }

    private fun createRequestBody(id: String): JsonObject {
        val gsonObject = JsonObject()
        try {
//            val jsonObj_ = JSONObject()
//            jsonObj_.put("carId", id)
//            val jsonParser = JsonParser()
//            gsonObject = jsonParser.parse(jsonObj_.toString()) as JsonObject

            gsonObject.addProperty("carId", id)


            //print parameter
            Log.e("gsonObject", "AS PARAMETER  $gsonObject")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("Exception", e.toString())

        }

        return gsonObject
    }
}
