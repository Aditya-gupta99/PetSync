package com.sparklead.petsync.firebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.sparklead.petsync.utils.Constants
import com.sparklead.petsync.DashboardActivity
import com.sparklead.petsync.R
import kotlin.random.Random

class FirebaseService : FirebaseMessagingService() {

    companion object {
        var sharedPref: SharedPreferences? = null

        var token: String?
            get() {
                return sharedPref?.getString(Constants.TOKEN, "")
            }
            set(value) {
                sharedPref?.edit()?.putString(Constants.TOKEN, value)?.apply()
            }
    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        print(token)
        token = p0
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)

        // checking for type = food and show notification
        if (p0.data["type"] == "food") {
            val intent = Intent(this, DashboardActivity::class.java)
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val notificationId = Random.nextInt()

            val notification: NotificationCompat.Builder =
                NotificationCompat.Builder(this, "demoChannel1")

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                createNotificationChannel(notificationManager)
            }

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            val pendingIntent =
                PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
            notification.setContentTitle(p0.data["title"])
                .setContentText(p0.data["message"])
                .setSmallIcon(R.drawable.baseline_notifications_24)
                .setAutoCancel(true)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(pendingIntent)
                .build()

            notificationManager.notify(notificationId, notification.notification)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager: NotificationManager) {

        val channelName = "PetSyncTest13"
        val channel = NotificationChannel(
            "demoChannel1", channelName,
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "PetSyncTest13"
            enableLights(true)
            enableVibration(true)
            lightColor = Color.WHITE
            setSound(
                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION),
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build()
            )
        }

        notificationManager.createNotificationChannel(channel)
    }
}