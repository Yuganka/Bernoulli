package co.kruzr.bernoulli;

import android.bluetooth.BluetoothAdapter;
import android.content.pm.PackageManager;
import android.provider.Settings;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

import co.kruzr.bernoulli.annotation.RequiresPermission;
import co.kruzr.bernoulli.annotation.RequiresSetting;
import co.kruzr.bernoulli.desilting.Dam;
import co.kruzr.bernoulli.managers.GPSManager;
import co.kruzr.bernoulli.managers.NetworkManager;

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
        List<RequiresSetting> showSettingsDialog = new ArrayList<>();

        for (RequiresPermission permission : stream.getRequiredPermissions())

            if (!isPermissionGranted(permission.permission()))

                switch (permission.permissionDisabledPolicy()) {

                    case PROCEED: // don't need to do nothing
                        break;

                    case FAIL:
                        shouldFlow = false;
                        break;

                    case ASK_IF_MISSING:
                        askPermissions.add(permission);
                        break;
                }

        if (askPermissions.size() == 0)
            for (RequiresSetting setting : stream.getRequiredSettings())
                if (!isSettingsStateMatching(setting))

                    switch (setting.settingsStateMismatchPolicy()) {

                        case PROCEED: // don't need to do nothing
                            break;

                        case FAIL:
                            shouldFlow = false;
                            break;

                        case SHOW_DIALOG_IF_STATE_MISMATCH:
                            showSettingsDialog.add(setting);
                            break;
                    }

        if (askPermissions.size() > 0 || showSettingsDialog.size() > 0) {
            dam = new Dam(StreamFlowState.CHOKED);
            dam.setAskPermissions(askPermissions);
            dam.setShowSettingsRequirementDialog(showSettingsDialog);
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
     * Checks whether a given setting's state (enabled or disabled) is matching what is specified in a
     * RequiresSetting annotation.
     *
     * @param requiresSetting the Settings type to check
     * @return                whether the setting's state (enabled or disabled) is matching the
     *                        settingsStateMismatchPolicy of the Setting.
     */
    private boolean isSettingsStateMatching(RequiresSetting requiresSetting) {

        boolean isEnabled = false;

        switch (requiresSetting.setting()) {

            case GPS:
                isEnabled = new GPSManager().isGPSTurnedOn();
                break;

            case BLUETOOTH:
                BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                isEnabled = bluetoothAdapter != null && bluetoothAdapter.isEnabled();
                break;

            case AUTO_ROTATE:
                isEnabled = Settings.System.getInt(BernoulliBank.getContext().getContentResolver(),
                        Settings.System.ACCELEROMETER_ROTATION, 0) == 1;
                break;

            case INTERNET_ANY:
                isEnabled = new NetworkManager().isInternetAvailable();
                break;

            case INTERNET_WIFI:
                isEnabled = new NetworkManager().isWifiAvailable();
                break;

            case INTERNET_MOBILE_DATA:
                isEnabled = new NetworkManager().isMobileInternetAvailable();
                break;

            case AIRPLANE_MODE:
                isEnabled = Settings.Global.getInt(BernoulliBank.getContext().getContentResolver(),
                        Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
        }

        return requiresSetting.shouldBeEnabled() == isEnabled;
    }
}