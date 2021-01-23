package com.nick_sib.testtaskappcraft.mvp.model.entity

data class AlbumInfo(
    val albumId: Int = -1,
    val id: Int = -1,
    val title: String = "empty",
    val url: String = "-", //в идеале в API бэкенда нужна каринка "sorry"
    val thumbnailUrl: String = "-"
)