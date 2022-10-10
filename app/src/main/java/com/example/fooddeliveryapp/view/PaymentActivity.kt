package com.example.fooddeliveryapp.view

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.RadioButton
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.ActivityPaymentBinding
import com.example.fooddeliveryapp.model.local.AppDatabase
import com.example.fooddeliveryapp.model.local.OrderDao
import com.example.fooddeliveryapp.model.local.OrderItem
import com.example.fooddeliveryapp.view.AddressAdapter.Companion.ADDRESS
import com.example.fooddeliveryapp.viewmodel.CheckoutViewModel


class PaymentActivity: AppCompatActivity() {
    private lateinit var binding: ActivityPaymentBinding
    private lateinit var paymentViewModel: CheckoutViewModel
    private lateinit var database: AppDatabase
    private lateinit var orderDao: OrderDao


    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "i.apps.notifications"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val alarmManager = getSystemService(ALARM_SERVICE)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        initView()
    }
    private fun initView() {
        paymentViewModel = CheckoutViewModel(application)
        database = AppDatabase.getInstance(this)
        orderDao = database.orderDao()
        val address = intent.getStringExtra(ADDRESS)
        binding.radioBtnScanQR.setOnClickListener {
            binding.imgPaypal.visibility = View.VISIBLE
        }
        binding.radioBtnCash.setOnClickListener {
            binding.imgPaypal.visibility = View.GONE
        }
        binding.radioBtnCredit.setOnClickListener {
            binding.imgPaypal.visibility = View.GONE
        }
        binding.radioBtnGooglePay.setOnClickListener {
            binding.imgPaypal.visibility = View.GONE
        }

        binding.btnConfirm.setOnClickListener {
            val paymentId = binding.radioPayment.checkedRadioButtonId
            val radioButton = findViewById<RadioButton>(paymentId)
            val paymentMethod = radioButton.text.toString()
            paymentViewModel.getPriceOfCart()
            paymentViewModel.finalTotal.observe(this){
                val order = OrderItem(0, it.toString(), address?:"", paymentMethod)
                orderDao.insertOrderItem(order)
            }
            confirmationNotification("Your order has been received by the restaurant and they are preparing your meal")

            val timer = object: CountDownTimer(10000, 10000) {
                override fun onTick(p0: Long) {
                }
                override fun onFinish() {
                    pickedUpNotification("Your delivery has been picked up by John and is on the way")
                }
            }
            timer.start()
            val intent = Intent(this, OrdersActivity::class.java)
            ContextCompat.startActivity(this, intent, null)

        }
    }
    private fun confirmationNotification(description: String) {
        val intent = Intent(this, afterNotification::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val contentView = RemoteViews(packageName, R.layout.activity_after_notification)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(this, channelId)
                .setContent(contentView)
                .setSmallIcon(R.drawable.food_delivery_logo)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.food_delivery_logo))
                .setContentIntent(pendingIntent)
        } else {

            builder = Notification.Builder(this)
                .setContent(contentView)
                .setSmallIcon(R.drawable.food_delivery_logo)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.food_delivery_logo))
                .setContentIntent(pendingIntent)
        }
        notificationManager.notify(1234, builder.build())
    }

    private fun pickedUpNotification(description: String) {
        val intent = Intent(this, pickupNotification::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val contentView = RemoteViews(packageName, R.layout.activity_pickup_notification)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(this, channelId)
                .setContent(contentView)
                .setSmallIcon(R.drawable.food_delivery_logo)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.food_delivery_logo))
                .setContentIntent(pendingIntent)
        } else {

            builder = Notification.Builder(this)
                .setContent(contentView)
                .setSmallIcon(R.drawable.food_delivery_logo)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.food_delivery_logo))
                .setContentIntent(pendingIntent)
        }
        notificationManager.notify(1234, builder.build())
    }
}
