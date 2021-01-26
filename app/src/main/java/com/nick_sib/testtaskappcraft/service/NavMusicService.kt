package com.nick_sib.testtaskappcraft.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.nick_sib.testtaskappcraft.R
import com.nick_sib.testtaskappcraft.ui.MainActivity

private const val CHANNEL_ID = "NavigationMusicServiceChannel"
private const val CHANNEL_NAME = "Navigation and music service channel"
const val ACTION_LOCATION_IN_NOTIFICATION= "ACTION_LOCATION_IN_NOTIFICATION"
const val ACTION_INTENT_SEND_LOCATION= "ACTION_INTENT_SEND_LOCATION"
const val KEY_DATA_LOCATION= "KEY_DATA_LOCATION"

const val REQUEST_LOCATION= 12345L
const val REQUEST_PERMISSION_LOCATION= 123

class NavMusicService: Service() {

    private lateinit var player: MediaPlayer


    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        val notificationIntent = Intent(this, MainActivity::class.java)
                .apply { action = ACTION_LOCATION_IN_NOTIFICATION }
        val pendingIntent = PendingIntent.getActivity(
                this, 0, notificationIntent, 0
        )
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Task 3 from App Craft")
                .setContentText("I can play music and follow you")
                .setSmallIcon(R.drawable.ic_navigate)
                .setContentIntent(pendingIntent)
                .build()
        startForeground(1, notification)

        player.start()
        LocateProvider(this).getDeviceLocation {

            val intentLocInfo = Intent(ACTION_INTENT_SEND_LOCATION)
                .apply { putExtra(KEY_DATA_LOCATION, it) }
            sendBroadcast(intentLocInfo)
        }

        return START_NOT_STICKY
    }

    override fun onCreate() {
        super.onCreate()

        initPlayer()
        createNotificationChannel()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.stop()
    }

    override fun onBind(intent: Intent?): IBinder? = null


    private fun initPlayer() {
        player = MediaPlayer.create(applicationContext, R.raw.sting_watching)
        player.isLooping = true
    }

    private fun createNotificationChannel() {
        val serviceChannel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val manager = getSystemService(
            NotificationManager::class.java
        )
            manager.createNotificationChannel(serviceChannel)
    }
}