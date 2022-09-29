package com.example.fooddeliveryapp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.fooddeliveryapp.R
import kotlinx.coroutines.NonDisposableHandle.parent

class CountrySpinnerAdapter(private var images: IntArray, private var codes: Array<String>) :
    BaseAdapter() {

    override fun getCount() = images.size

    override fun getItem(i: Int) = null

    override fun getItemId(i: Int) = 0.toLong()

    override fun getView(i: Int, view: View, viewGroup: ViewGroup): View {
        val inflater = LayoutInflater.from(view.context)
        val view = inflater.inflate(R.layout.country_spinner_item,null)
        val icon = view.findViewById<View>(R.id.imgFlag) as ImageView
        val names = view.findViewById<View>(R.id.txtCountry) as TextView
        icon.setImageResource(images[i])
        names.text = codes[i]
        return view
    }
}