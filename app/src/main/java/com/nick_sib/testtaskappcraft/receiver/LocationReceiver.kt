package com.nick_sib.testtaskappcraft.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.nick_sib.testtaskappcraft.mvp.model.entity.LocationMessage
import com.nick_sib.testtaskappcraft.service.ACTION_INTENT_SEND_LOCATION
import com.nick_sib.testtaskappcraft.service.KEY_DATA_LOCATION

class LocationReceiver (
    private val updateLocation: (LocationMessage) -> Unit
) : BroadcastReceiver() {

    private val intentFilter by lazy { IntentFilter(ACTION_INTENT_SEND_LOCATION) }

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.run {
            if (action == ACTION_INTENT_SEND_LOCATION) {
                val locInfo: LocationMessage? = getParcelableExtra(KEY_DATA_LOCATION)
                locInfo?.let { updateLocation(it) }
            }
        }
    }

    fun register(context: Context) {
        context.registerReceiver(this, intentFilter)
    }

    fun unregister(context: Context) {
        context.unregisterReceiver(this)
    }
}