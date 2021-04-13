package co.kruzr.bernoulli;

import android.bluetooth.BluetoothAdapter;
import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;

import co.kruzr.bernoulli.annotation.RequiresPermission;
import co.kruzr.bernoulli.annotation.RequiresSetting;
import co.kruzr.bernoulli.managers.GPSManager;

/**
 * This class is responsible for evaluating the actual state of the permission and setting requirements for a given
 * Stream object (i.e. method).
 */
class FlowStateEvaluator {

    /**
     * Evaluates whether, for a given Stream, the permissions and settings it requires have been granted / enabled.
     *
     * @param stream   the method for which the permissions and settings need to be checked
     * @return         an EvaluatedStream object that encapsulates the missing permissions and settings for the stream
     */
    public EvaluatedStream evaluate(Stream stream) {

        EvaluatedStream evaluatedStream = new EvaluatedStream(stream);

        for (RequiresPermission permission : stream.getRequiredPermissions()) {
            if (!isPermissionGranted(permission.permission()) && permission.disabledPolicy() == DisabledPolicy.FAIL)
                evaluatedStream.getMissingPermissions().add(permission.permission());
        }

        for (RequiresSetting settings : stream.getRequiredSettings())
            if (!isSettingsGranted(settings.setting()) && settings.disabledPolicy() == DisabledPolicy.FAIL)
                evaluatedStream.getMissingSettings().add(settings.setting());

        return evaluatedStream;
    }

    /**
     * Checks whether a given permission has been granted.
     *
     * @param permission the Permission to check
     * @return whether the permission has been granted or not
     */
    private boolean isPermissionGranted(Permission permission) {
        return ContextCompat.checkSelfPermission(BernoulliBank.getContext(), permission.toString()) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Checks whether a given setting is enabled.
     *
     * @param setting the Settings type to check
     * @return whether the setting is enabled
     */
    private boolean isSettingsGranted(Settings setting) {

        switch (setting) {

            case GPS:
                return new GPSManager(BernoulliBank.getContext()).isGPSTurnedOn();

            case BLUETOOTH:
                BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                return bluetoothAdapter != null && bluetoothAdapter.isEnabled();

            case AUTO_ROTATE:
                return android.provider.Settings.System.getInt(BernoulliBank.getContext().getContentResolver(),
                        android.provider.Settings.System.ACCELEROMETER_ROTATION, 0) == 1;
            default:
                return false;
        }
    }
}