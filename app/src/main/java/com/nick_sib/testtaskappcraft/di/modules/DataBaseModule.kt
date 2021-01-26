package com.nick_sib.testtaskappcraft.di.modules

import androidx.room.Room
import com.nick_sib.testtaskappcraft.App
import com.nick_sib.testtaskappcraft.mvp.model.entity.room.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Singleton
    @Provides
    fun database(app: App): Database = Room.databaseBuilder(
        app,
        Database::class.java,
        Database.DB_NAME
    ).build()

}