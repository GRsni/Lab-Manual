package uca.esi.manual

import android.app.Application
import timber.log.Timber

/**
 * Manual application
 *
 * @constructor Create empty Manual application
 */
class ManualApplication : Application() {
    /**
     * On create
     *
     */
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}