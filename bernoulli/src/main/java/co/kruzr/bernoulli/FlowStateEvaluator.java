package co.kruzr.bernoulli;

import android.bluetooth.BluetoothAdapter;
import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

import co.kruzr.bernoulli.annotation.RequiresPermission;
import co.kruzr.bernoulli.annotation.RequiresSetting;
import co.kruzr.bernoulli.desilting.Dam;
import co.kruzr.bernoulli.managers.GPSManager;

/**
 * This class is responsible for evaluating the actual state of the permission and setting requirements for a given
 * Stream object (i.e. method).
 */
class FlowStateEvaluator {

    /**
     * Evaluates whether, for a given Stream, the permissions and settings it requires have been granted / enabled.
     *
     * @param stream the method for which the permissions and settings need to be checked
     * @return an EvaluatedStream object that encapsulates the missing permissions and settings for the stream
     */
    public Dam evaluate(Stream stream) {

        Dam dam;

        boolean shouldFlow = true;

        List<RequiresPermission> askPermissions = new ArrayList<>();
        List<RequiresSetting> askSettings = new ArrayList<>();

        for (RequiresPermission permission : stream.getRequiredPermissions())
            if (!isPermissionGranted(permission.permission()))

                switch (permission.disabledPolicy()) {

                    case PROCEED: // don't need to do nothing
                        break;
                    case FAIL:
                        shouldFlow = false;
                        break;
                    case ASK_IF_MISSING:
                    case ASK_IF_MISSING_AND_SHOW_RATIONALE_IF_DENIED:
                        askPermissions.add(permission);
                        break;
                }

        for (RequiresSetting setting : stream.getRequiredSettings())
            if (!isSettingsGranted(setting.setting()))

                switch (setting.disabledPolicy()) {

                    case PROCEED: // don't need to do nothing
                        break;
                    case FAIL:
                        shouldFlow = false;
                        break;
                    case ASK_IF_MISSING:
                    case ASK_IF_MISSING_AND_SHOW_RATIONALE_IF_DENIED:
                        askSettings.add(setting);
                        break;
                }

        if (askPermissions.size() > 0 || askSettings.size() > 0) {
            dam = new Dam(StreamFlowState.CHOKED);
            dam.setAskPermissions(askPermissions);
            dam.setAskSettings(askSettings);
        } else
            dam = new Dam(shouldFlow ? StreamFlowState.FLOW : StreamFlowState.STAGNATE);

        return dam;
    }

    /**
     * Checks whether a given permission has been granted.
     *
     * @param permission the Permission to check
     * @return whether the permission has been granted or not
     */
    private boolean isPermissionGranted(Permission permission) {
        return ContextCompat.checkSelfPermission(BernoulliBank.getContext(), permission.getPermissionName()) == PackageManager.PERMISSION_GRANTED;
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