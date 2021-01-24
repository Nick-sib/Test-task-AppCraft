package com.nick_sib.testtaskappcraft.mvp.model.entity.room.dao

import androidx.room.*
import com.nick_sib.testtaskappcraft.mvp.model.entity.AlbumData

@Dao
interface AlbumDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(albumData: AlbumData)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(albumDataList: List<AlbumData>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg albumDataArg: AlbumData)

    @Query("SELECT * FROM ALBUM_DATA")
    fun getAll(): List<AlbumData>?
    @Query("SELECT * FROM ALBUM_DATA WHERE ID =:albumId")
    fun getById(albumId: Int): List<AlbumData>?

    @Delete
    fun deleteAlbumData(albumData: AlbumData)
}