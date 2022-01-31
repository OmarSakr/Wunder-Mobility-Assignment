package com.codevalley.wundermobilityassignment.utils

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.codevalley.wundermobilityassignment.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker


class InfoWindowAdapter(mContext: Context) : GoogleMap.InfoWindowAdapter {
    @SuppressLint("InflateParams")
    var mWindow: View = LayoutInflater.from(mContext).inflate(R.layout.info_window, null)

    private fun setInfoWindowText(marker: Marker) {
        val title = marker.title
        val tvTitle = mWindow.findViewById<TextView>(R.id.tvTitle)
        if (!TextUtils.isEmpty(title)) {
            tvTitle.text = title
        }
    }

    override fun getInfoWindow(p0: Marker): View {
        setInfoWindowText(p0)
        return mWindow
    }

    override fun getInfoContents(p0: Marker): View {
        setInfoWindowText(p0)
        return mWindow
    }
}