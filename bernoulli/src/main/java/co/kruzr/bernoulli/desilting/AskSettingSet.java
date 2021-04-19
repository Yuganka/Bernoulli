package co.kruzr.bernoulli.desilting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;

import java.util.List;

import co.kruzr.bernoulli.CurrentScreen;
import co.kruzr.bernoulli.annotation.RequiresSetting;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AskSettingSet {

    private final List<RequiresSetting> requiresSettings;

    public void begin() {

        Log.e("Bernoulli", "AskSettingSet begin");

        if (CurrentScreen.getCurrentActivity() != null) {

            Log.e("Bernoulli", "AskSettingSet Current activity not null");

            new AlertDialog.Builder(CurrentScreen.getCurrentActivity())
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

    private String createMessage(RequiresSetting requiresSetting) {

        Log.e("Bernoulli", "creating message");

        return "Please ensure that "
                + requiresSetting.setting().getDescription() + " is "
                + (requiresSetting.shouldBeEnabled() ? "enabled." : "disabled.");
    }
}
