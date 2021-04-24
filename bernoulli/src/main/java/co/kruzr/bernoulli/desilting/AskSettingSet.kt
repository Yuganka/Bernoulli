package co.kruzr.bernoulli.desilting

import android.R
import android.app.AlertDialog
import android.content.DialogInterface
import android.util.Log
import co.kruzr.bernoulli.CurrentScreen.currentActivity
import co.kruzr.bernoulli.annotation.RequiresSetting

/**
 * Helper class that works as starting point for showing the dialog to the user to enable/disable the settings that
 * are required by a specific method and which have set SettingsStateMismatchPolicy as SettingsStateMismatchPolicy
 * .SHOW_DIALOG.
 */
internal class AskSettingSet(
        /**
         * The list of settings required by a specific method.
         */
        private val requiresSettings: List<RequiresSetting> = arrayListOf()) {

    /**
     * Start showing dialog for the setting state requirements for a specific method
     */
    fun begin() {

        Log.e("Bernoulli", "AskSettingSet begin")

        if (currentActivity != null) {

            Log.e("Bernoulli", "AskSettingSet Current activity not null")

            // Do not convert the positive button dialog interface to lambda, will impede execution
            if (requiresSettings.size == 1)
                AlertDialog.Builder(currentActivity)
                        .setTitle(requiresSettings[0].setting.description)
                        .setMessage(createMessage(requiresSettings[0]))
                        .setPositiveButton(R.string.ok, DialogInterface.OnClickListener { dialog, _ -> dialog.dismiss() })
                        .show()
            else
                AlertDialog.Builder(currentActivity)
                        .setTitle("Settings")
                        .setMessage(consolidatedMessage)
                        .setPositiveButton(R.string.ok) { dialog, _ -> dialog.dismiss() }
                        .show()
        }
    }

    /**
     * Creates the message to be shown in the dialog.
     *
     * @param requiresSetting the setting for which the dialog needs to be shown.
     * @return the message that needs to be shown
     */
    private fun createMessage(requiresSetting: RequiresSetting): String {
        Log.e("Bernoulli", "Creating message for 1 setting")
        return ("Please ensure that "
                + requiresSetting.setting.description + " is "
                + if (requiresSetting.shouldBeEnabled) "enabled." else "disabled.")
    }

    /**
     * Creates a consolidated message for multiple settings.
     *
     * @return the message that needs to be shown
     */
    private val consolidatedMessage: String
        get() {
            Log.e("Bernoulli", "Creating message for >1 settings")

            val message = StringBuilder("Please ensure that \n\n")

            for (requiresSetting in requiresSettings)
                message.append("- ")
                        .append(requiresSetting.setting.description)
                        .append(" is ")
                        .append(if (requiresSetting.shouldBeEnabled) "enabled" else "disabled")
                        .append("\n")

            return message.toString()
        }
}