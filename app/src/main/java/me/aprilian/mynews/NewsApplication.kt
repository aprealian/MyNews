package me.aprilian.mynews

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import timber.log.Timber

class NewsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setDarkMode(isEnable = false)
        setupTimber()
    }

    private fun setDarkMode(isEnable: Boolean) {
        if (!isEnable) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun setupTimber(){
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }




}