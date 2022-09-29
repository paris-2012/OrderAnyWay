package com.example.fooddeliveryapp.view

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.FragmentEmailRegistrationBinding
import com.example.fooddeliveryapp.viewmodel.AuthenticationViewModel

class EmailRegistrationFragment(private val activity: Activity) : Fragment(R.layout.fragment_email_registration) {
    private var _binding: FragmentEmailRegistrationBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmailRegistrationBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewModel = AuthenticationViewModel(activity)
        super.onViewCreated(view, savedInstanceState)
        binding.btnSubmit.setOnClickListener {
            viewModel.addEmailAccount(binding.edtEmail.text.toString(), binding.edtPassword.text.toString(), view?.context)
        }
    }
}