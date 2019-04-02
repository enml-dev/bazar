package com.enml.bazar.app

import android.app.Application
import android.content.Context
import com.enml.bazar.data.model.*
import com.enml.bazar.utils.Constants.PARSE_APP_ID
import com.enml.bazar.utils.Constants.PARSE_SERVER_URL
import com.parse.Parse
import com.parse.ParseObject

class App : Application() {

    companion object {
        var ctx: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        ctx = applicationContext

        ParseObject.registerSubclass(Item::class.java)
        ParseObject.registerSubclass(Category::class.java)
        ParseObject.registerSubclass(SubCategory::class.java)
        ParseObject.registerSubclass(ItemType::class.java)
        ParseObject.registerSubclass(Province::class.java)
        ParseObject.registerSubclass(Municipality::class.java)

        // Initialize the access to the Parse server.
        Parse.initialize(
                Parse.Configuration.Builder(this).applicationId(PARSE_APP_ID)
                        .clientKey("")
                        .server(PARSE_SERVER_URL)
                        .enableLocalDataStore()// The trailing slash is important.
                        .build())

    }

}