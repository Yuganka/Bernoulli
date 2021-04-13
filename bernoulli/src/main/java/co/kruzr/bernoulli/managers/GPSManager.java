package co.kruzr.bernoulli.managers;

import android.content.Context;
import android.provider.Settings;

import lombok.AllArgsConstructor;

/**
 * A class to perform GPS related checks.
 */
@AllArgsConstructor
public class GPSManager {

    private final Context context;

    /**
     * @return  true if the GPS is ON, else false
     */
    public boolean isGPSTurnedOn() {

        boolean returnValue = false;

        int locationMode = 0;

        try {
            locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);

        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            returnValue = false;
        }

        if (locationMode != Settings.Secure.LOCATION_MODE_OFF)
            returnValue = true;

        return returnValue;
    }
}
