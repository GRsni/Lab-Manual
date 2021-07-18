package uca.esi.manual

import android.app.Application
import timber.log.Timber

class ManualApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}