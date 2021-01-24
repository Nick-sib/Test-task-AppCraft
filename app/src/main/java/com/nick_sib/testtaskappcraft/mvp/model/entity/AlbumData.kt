package com.nick_sib.testtaskappcraft.mvp.model.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "ALBUM_DATA")
data class AlbumData (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "ID")
    val id: Int = -1,
    val userId: Int = -1,
    val title: String = "empty",
): Parcelable