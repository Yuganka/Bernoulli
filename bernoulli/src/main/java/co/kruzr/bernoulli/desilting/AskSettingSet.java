package co.kruzr.bernoulli.desilting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;

import java.util.List;

import co.kruzr.bernoulli.CurrentScreen;
import co.kruzr.bernoulli.annotation.RequiresSetting;
import lombok.AllArgsConstructor;

/**
 * Helper class that works as starting point for showing the dialog to the user to enable/disable the settings that
 * are required by a specific method and which have set SettingsStateMismatchPolicy as SettingsStateMismatchPolicy
 * .SHOW_DIALOG_IF_STATE_MISMATCH.
 */
@AllArgsConstructor
public class AskSettingSet {

    /**
     * The list of settings required by a specific method.
     */
    private final List<RequiresSetting> requiresSettings;

    /**
     * Start showing dialog for the setting state requirements for a specific method
     */
    public void begin() {

        Log.e("Bernoulli", "AskSettingSet begin");

        if (CurrentScreen.INSTANCE.getCurrentActivity() != null) {

            Log.e("Bernoulli", "AskSettingSet Current activity not null");

            new AlertDialog.Builder(CurrentScreen.INSTANCE.getCurrentActivity())
                    .setTitle(requiresSettings.get(0).setting().getDescription())
                    .setMessage(createMessage(requiresSettings.get(0)))
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .show();
        }
    }

    /**
     * Creates the message to be shown in the dialog.
     *
     * @param requiresSetting   the setting for which the dialog needs to be shown.
     * @return                  the message that needs to be shown
     */
    private String createMessage(RequiresSetting requiresSetting) {

        Log.e("Bernoulli", "creating message");

        return "Please ensure that "
                + requiresSetting.setting().getDescription() + " is "
                + (requiresSetting.shouldBeEnabled() ? "enabled." : "disabled.");
    }
}
