package com.example.fooddeliveryapp.model.remote

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.view.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.time.format.TextStyle
import kotlin.random.Random

class MyFcmService: FirebaseMessagingService() {

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("ServiceCast")
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val title = message.data["title"]
        val msg = message.data["message"]
        val jobId = message.data["job_id"]
        val jobTitle = message.data["job_title"]

        val jIntent = Intent(baseContext, MainActivity::class.java)
        jIntent.apply {
            putExtra("jobId", jobId)
            putExtra("jobTitle", jobTitle)
            putExtra("msg", msg)
        }
        val id = Random.nextInt(0, Int.MAX_VALUE)

        val pIntent = PendingIntent.getActivity(
            baseContext,
            id,
            jIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification = Notification.Builder(baseContext, "JobAlerts").apply {
            setContentTitle(title)
            setContentText(msg)
            setContentIntent(pIntent)   // Defines which activity to launch
            setStyle(Notification.BigTextStyle().apply {
                bigText(msg)
                setSummaryText(msg)
                }
            )
            setSmallIcon(R.drawable.ic_baseline_person_100)
            setAutoCancel(true)
        }.build()


        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
            val channel = NotificationChannel("JobAlerts","Job Applications",  NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(id, notification)
    }
}