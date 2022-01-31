package com.codevalley.wundermobilityassignment.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class LocationCache @Inject constructor(@ApplicationContext mContext: Context) {
    private val locationCacheLat = "locationCache"
    private val locationCacheLng = "locationCache"
    val context = mContext


    fun getLat(): String {
        val sharedPreferences = context.getSharedPreferences(
            locationCacheLat, 0
        )
        return sharedPreferences.getString("lat", "").toString()
    }

    fun setLat(lat: String) {
        val sharedPreferences = context.getSharedPreferences(
            locationCacheLat,
            0
        )
        val editor = sharedPreferences.edit()
        editor.putString("lat", lat)
        editor.apply()
    }

    fun getLng(): String {
        val sharedPreferences = context.getSharedPreferences(
            locationCacheLng, 0
        )
        return sharedPreferences.getString("lng", "").toString()
    }

    fun setLng(lat: String) {
        val sharedPreferences = context.getSharedPreferences(
            locationCacheLng,
            0
        )
        val editor = sharedPreferences.edit()
        editor.putString("lng", lat)
        editor.apply()
    }


}