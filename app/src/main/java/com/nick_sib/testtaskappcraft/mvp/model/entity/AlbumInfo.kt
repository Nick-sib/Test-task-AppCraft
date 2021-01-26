package com.nick_sib.testtaskappcraft.mvp.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ALBUM_INFO")
data class AlbumInfo(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "ID")
    val id: Int = -1,
    @ColumnInfo(name = "ALBUM_ID")
    val albumId: Int = -1,
    val title: String = "empty",
    val url: String = "-", //в идеале в API бэкенда нужна каринка "sorry"
    val thumbnailUrl: String = "-"
)