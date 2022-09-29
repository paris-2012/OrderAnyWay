package com.example.fooddeliveryapp.view

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.FragmentPhoneRegistrationBinding
import com.example.fooddeliveryapp.viewmodel.AuthenticationViewModel


class PhoneRegistrationFragment(private val activity: Activity) : Fragment(R.layout.fragment_phone_registration) {
    private var _binding: FragmentPhoneRegistrationBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhoneRegistrationBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mySpinner = binding.countrySpinner
        val spinnerObjects = ArrayList<String>()
        spinnerObjects.apply {
            add("USA")
            add("China")
            add("India")
        }
        val adapter = ArrayAdapter(view?.context!!, android.R.layout.simple_list_item_1, spinnerObjects)

        mySpinner.adapter = adapter
        val spin = binding.countrySpinner
        spin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
        val flags = intArrayOf(
            R.drawable.united_24x24_33135,
            R.drawable.china_24x24_32942,
            R.drawable.india_24x24_32988,
            R.drawable.united_24x24_33115,
            R.drawable.canada_24x24_32938,
            R.drawable.austallia_24x24_32912)
        val codes = resources.getStringArray(R.array.country_codes)
        val customAdapter = CountrySpinnerAdapter(flags, codes)
        spin.adapter = customAdapter
        val viewModel = AuthenticationViewModel(activity)
        super.onViewCreated(view, savedInstanceState)
        binding.btnSendOTP.setOnClickListener {
            val phoneNum = "+1" + binding.edtPhone.text.toString()
            Log.i("phone", phoneNum)
            viewModel.sendOTP(phoneNum)
        }
        binding.btnSubmit.setOnClickListener {
            val phoneNum = "+1" + binding.edtPhone.text.toString()
            Log.i("phone", phoneNum)
            viewModel.addPhoneAccount(phoneNum, binding.edtPassword.text.toString())
        }

    }

    private fun makeToast(context: Context, s: String) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show()
    }
}