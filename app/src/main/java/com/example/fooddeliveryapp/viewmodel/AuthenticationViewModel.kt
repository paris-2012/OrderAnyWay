package com.example.fooddeliveryapp.viewmodel

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.fooddeliveryapp.view.HomeActivity
import com.example.fooddeliveryapp.view.RegistrationActivity
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit

class AuthenticationViewModel(private val activity: Activity) {
    private lateinit var auth: FirebaseAuth
    private lateinit var currentUser: FirebaseUser
    private lateinit var storeVerificationId:String
    private lateinit var resendToken:PhoneAuthProvider.ForceResendingToken
    private lateinit var callBack: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    fun addEmailAccount(email: String, password: String, context: Context) {
        auth = Firebase.auth
        if (this::currentUser.isInitialized) {
            if (auth.currentUser != null && currentUser != auth) {
                currentUser = auth.currentUser as FirebaseUser
            }
        }
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if (auth.currentUser != null) {
                        currentUser = auth.currentUser as FirebaseUser
                    }
                    sendEmailVerification()
                    makeToast(context, "Account created successfully")
                } else {
                    Log.i("tag", "error did not create successfully!")
                    makeToast(context, "Account not created successfully")
                }
            }
    }

    private fun sendEmailVerification() {
        currentUser.let {
            currentUser.sendEmailVerification()
        }
    }

    private fun setUpCallBack() {
        callBack = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                Log.i("tag", "onVerificationCompleted")
                activity.finish()

            }

            override fun onVerificationFailed(p0: FirebaseException) {
                Log.i("tag", "onVerificationFailed")
            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                storeVerificationId = p0
                resendToken = p1
                val intent = Intent(activity.baseContext, RegistrationActivity::class.java)
                intent.putExtra("storedVerificationId", storeVerificationId)
                startActivity(activity.baseContext, intent, null)
                activity.finish()
            }
        }
    }

    fun sendOTP(phoneNumber: String) {

        setUpCallBack()
        auth = Firebase.auth

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(callBack)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun addGoogleAccount() {

    }

    private fun makeToast(context: Context, s: String) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show()
    }

}