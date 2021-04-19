package co.kruzr.bernoulli.managers;

import android.provider.Settings;

import co.kruzr.bernoulli.BernoulliBank;

/**
 * A class to perform GPS related checks.
 */
public class GPSManager {

    /**
     * @return true if the GPS is ON, else false
     */
    public boolean isGPSTurnedOn() {

        boolean returnValue = false;

        int locationMode = 0;

        try {
            locationMode = Settings.Secure.getInt(BernoulliBank.getContext().getContentResolver(), Settings.Secure.LOCATION_MODE);

        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            returnValue = false;
        }

        if (locationMode != Settings.Secure.LOCATION_MODE_OFF)
            returnValue = true;

        return returnValue;
    }
}
