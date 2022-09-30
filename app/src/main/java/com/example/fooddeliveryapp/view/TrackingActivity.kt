package com.example.fooddeliveryapp.view

import android.graphics.Color
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

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val chapelHill = LatLng(35.9132, -79.0558)
        mMap.addMarker(MarkerOptions().position(chapelHill).title("marker in Chapel Hill"))
        val durham = LatLng(35.9940, -78.8986)
        mMap.addMarker(MarkerOptions().position(durham).title("marker in Durham"))
        val raleigh = LatLng(35.7796, -78.6382)
        mMap.addMarker(MarkerOptions().position(raleigh).title("marker in Raleigh"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(chapelHill, 9f))

        mMap.addPolygon(
            PolygonOptions().add(chapelHill, durham, raleigh)
                .strokeWidth(3f)
                .fillColor(Color.TRANSPARENT)
                .geodesic(true)
                .strokeColor(Color.RED)
        )
        mMap.addCircle(
            CircleOptions().radius(3000.00)
                .center(raleigh).fillColor(Color.RED)
                .strokeWidth(0f)
        )
        mMap.addCircle(
            CircleOptions().radius(3000.00)
                .center(durham).fillColor(Color.BLUE)
                .strokeWidth(0f)
        )
        mMap.addCircle(
            CircleOptions().radius(3000.00)
                .center(chapelHill).fillColor(Color.rgb(141, 200, 240))
                .strokeWidth(0f)
        )
        mMap.addPolyline(
            PolylineOptions().add(chapelHill).add(LatLng(35.9132+.1, -79.0558+.1)).add(LatLng(35.9132+.1, -79.0558-.1)).add(
                LatLng(35.9132-.1, -79.0558-.1)
            )
        )
    }
}