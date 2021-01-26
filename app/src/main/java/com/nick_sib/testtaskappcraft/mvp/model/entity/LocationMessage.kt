package com.nick_sib.testtaskappcraft.mvp.model.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LocationMessage(
    val latitude: Double,
    val longitude: Double): Parcelable