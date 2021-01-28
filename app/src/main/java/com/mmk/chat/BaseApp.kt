package com.mmk.chat

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by mirzemehdi on 1/27/21
 */


@HiltAndroidApp
class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }


}