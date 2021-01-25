package com.nick_sib.testtaskappcraft.service


import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.nick_sib.testtaskappcraft.mvp.model.entity.LocationMessage


private const val INTERVAL = 500L

class LocateProvider(private val context: Context) {

    private val locationRequest: LocationRequest = LocationRequest.create()
            .setInterval(REQUEST_LOCATION)
            .setFastestInterval(INTERVAL)
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)


    @SuppressLint("MissingPermission")
    fun getDeviceLocation(onLocationReceiver: (LocationMessage) -> Unit) {
        LocationServices.getFusedLocationProviderClient(context)
            .requestLocationUpdates(locationRequest, object: LocationCallback(){
                override fun onLocationResult(locationResult: LocationResult?) {
                    super.onLocationResult(locationResult)
                    locationResult?.run {
                        for (loc in locations) {
                            loc?.run {
                                onLocationReceiver(
                                    LocationMessage(
                                        latitude,
                                        longitude)
                                )
                            }
                        }
                    }
                }
            }, null)
    }

}