package hr.ferit.stefanbelic.mushroom

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MushroomApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}