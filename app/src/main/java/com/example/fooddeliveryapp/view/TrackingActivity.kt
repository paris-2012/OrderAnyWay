package com.example.fooddeliveryapp.view

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.ActivityTrackingBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*


class TrackingActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityTrackingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrackingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val deliveryIcon =
            getMarkerIconFromDrawable(resources.getDrawable(R.drawable.ic_baseline_directions_car_50_bright_orange))
        val homeIcon =
            getMarkerIconFromDrawable(resources.getDrawable(R.drawable.ic_baseline_home_50_bright_orange))
        val storeIcon =
            getMarkerIconFromDrawable(resources.getDrawable(R.drawable.ic_baseline_storefront_50_bright_orange))
        val homeLocation = LatLng(35.141060, -79.003170)
        val deliveryLocation = LatLng(35.141060 + .001, -79.003170 + .001)
        val storeLocation = LatLng(35.141060 + .002, -79.003170 + .002)
        mMap.addMarker(
            MarkerOptions()
                .position(deliveryLocation)
                .icon(deliveryIcon)
        )
        mMap.addMarker(
            MarkerOptions()
                .position(homeLocation)
                .icon(homeIcon)
        )
        mMap.addMarker(
            MarkerOptions()
                .position(storeLocation)
                .icon(storeIcon)
        )

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(deliveryLocation, 16f))
    }
    private fun getMarkerIconFromDrawable(drawable: Drawable): BitmapDescriptor? {
        val canvas = Canvas()
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        canvas.setBitmap(bitmap)
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        drawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}
