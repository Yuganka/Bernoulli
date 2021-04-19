package co.kruzr.bernoulli.managers

import android.content.Context
import android.provider.Settings
import android.provider.Settings.SettingNotFoundException

/**
 * A class to perform GPS related checks.
 */
internal class GPSManager constructor(private val context: Context){

    /**
     * @return true if the GPS is ON, else false
     */
    val isGPSTurnedOn: Boolean

        get() {

            var returnValue = false
            var locationMode = 0

            try {
                locationMode = Settings.Secure.getInt(context.contentResolver,
                        Settings.Secure.LOCATION_MODE)
            } catch (e: SettingNotFoundException) {
                e.printStackTrace()
                returnValue = false
            }

            if (locationMode != Settings.Secure.LOCATION_MODE_OFF) returnValue = true

            return returnValue
        }
}