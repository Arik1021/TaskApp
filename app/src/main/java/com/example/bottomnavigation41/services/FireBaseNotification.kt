package com.example.bottomnavigation41.services


import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.bottomnavigation41.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class FireBaseNotification : FirebaseMessagingService() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(message: RemoteMessage) {
        Log.e("ololo", "onMessageReceived: " + message.notification?.title)
        Log.e("ololo", "onMessageReceived: " + message.notification?.body)
       sendNotification(message)
        super.onMessageReceived(message)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun sendNotification(remoteMessage: RemoteMessage) {
        val notificationBuilder = NotificationCompat.Builder(this, "task_chanel_id")
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher)
        notificationBuilder.setContentTitle(remoteMessage.notification?.title)
        notificationBuilder.setContentText(remoteMessage.notification?.body)
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val chanel = NotificationChannel(
            "task_chanel_id",
            "Channel human readable title",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(chanel)
        notificationManager.notify(1,notificationBuilder.build())
    }

}