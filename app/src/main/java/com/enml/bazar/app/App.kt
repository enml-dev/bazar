package com.enml.bazar.app

import android.app.Application
import android.content.Context
import com.enml.bazar.data.dao.factory.DAOFactory
import com.enml.bazar.data.dao.factory.DAOFactoryType
import com.enml.bazar.data.utils.DbHelper
import com.enml.bazar.utils.MediaLoader
import com.yanzhenjie.album.AlbumConfig
import com.yanzhenjie.album.Album

class App : Application() {

    companion object {
        var ctx: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        ctx = applicationContext

        DbHelper.init(this);

        DAOFactory.init(this, DAOFactoryType.PARSE)

        Album.initialize(AlbumConfig.newBuilder(this)
                .setAlbumLoader(MediaLoader())
                .build())

    }

}