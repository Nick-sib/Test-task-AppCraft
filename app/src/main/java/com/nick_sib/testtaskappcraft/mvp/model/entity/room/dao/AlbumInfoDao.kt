package com.nick_sib.testtaskappcraft.mvp.model.entity.room.dao

import androidx.room.*
import com.nick_sib.testtaskappcraft.mvp.model.entity.AlbumInfo


@Dao
interface AlbumInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(albumInfo: AlbumInfo)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(albumInfoList: List<AlbumInfo>): List<Long>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg albumInfoArg: AlbumInfo)

    @Query("SELECT * FROM ALBUM_INFO")
    fun getAll(): List<AlbumInfo>?
    @Query("SELECT * FROM ALBUM_INFO WHERE ALBUM_ID =:albumId")
    fun getInfoById(albumId: Int): List<AlbumInfo>?

    @Query("DELETE FROM ALBUM_INFO WHERE ALBUM_ID =:albumId")
    fun deleteAlbumInfo(albumId: Int)
}