package co.kruzr.bernoulli.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import co.kruzr.bernoulli.Permission;
import co.kruzr.bernoulli.PermissionDisabledPolicy;
import co.kruzr.bernoulli.Settings;
import co.kruzr.bernoulli.SettingsStateMismatchPolicy;
import co.kruzr.bernoulli.android.BernoulliActivity;
import co.kruzr.bernoulli.annotation.RequiresPermission;
import co.kruzr.bernoulli.annotation.RequiresSetting;
import co.kruzr.bernoulli.app.R;

/**
 * An activity to test all combinations of a fixed PermissionDisabledPolicy value of
 * PermissionDisabledPolicy.PROCEED and all possible values of SettingsStateMismatchPolicy.
 */
public class MyBernoulliActivityProceed extends BernoulliActivity implements View.OnClickListener {

    private final int REQUEST_CODE_FINE_LOCATION = 123;
    private final int REQUEST_CODE_CAMERA = 456;
    private final int REQUEST_CODE_RECORD_AUDIO = 789;

    private TextView textviewLogs;
    private Button button1, button2, button3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bernoulli_proceed);

        textviewLogs = findViewById(R.id.textview_logs);

        button1 = findViewById(R.id.button_1);
        button2 = findViewById(R.id.button_2);
        button3 = findViewById(R.id.button_3);
    }

    @Override
    protected void onStart() {
        super.onStart();

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

        findViewById(R.id.open_other).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyBernoulliActivityProceed.this, MyBernoulliActivityFail.class));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("Bernoulli", "MBA Hashcode " + hashCode());
    }

    @Override
    public void onClick(View v) {

        clearTextViewLogs();

        switch (v.getId()) {
            case R.id.button_1:
                button1();
                break;

            case R.id.button_2:
                button2();
                break;

            case R.id.button_3:
                button3();
                break;
        }
    }

    @RequiresPermission(permission = Permission.CAMERA, permissionDisabledPolicy =
            PermissionDisabledPolicy.PROCEED, permissionRequestCode = REQUEST_CODE_CAMERA)
    @RequiresSetting(setting = Settings.GPS, shouldBeEnabled = true, settingsStateMismatchPolicy =
            SettingsStateMismatchPolicy.PROCEED)
    private void button1() {

        textviewLogs.setText("Run Setting proceed");
    }

    @RequiresPermission(permission = Permission.ACCESS_FINE_LOCATION, permissionDisabledPolicy =
            PermissionDisabledPolicy.PROCEED, permissionRequestCode = REQUEST_CODE_FINE_LOCATION)
    @RequiresSetting(setting = Settings.GPS, shouldBeEnabled = true, settingsStateMismatchPolicy =
            SettingsStateMismatchPolicy.FAIL)
    private void button2() {

        textviewLogs.setText("Run Setting fail");
    }

    @RequiresPermission(permission = Permission.RECORD_AUDIO, permissionDisabledPolicy =
            PermissionDisabledPolicy.PROCEED, permissionRequestCode = REQUEST_CODE_RECORD_AUDIO)
    @RequiresSetting(setting = Settings.GPS, shouldBeEnabled = true, settingsStateMismatchPolicy =
            SettingsStateMismatchPolicy.SHOW_DIALOG_IF_STATE_MISMATCH)
    private void button3() {

        textviewLogs.setText("Run Setting show dialog");
    }

    private void clearTextViewLogs() {
        textviewLogs.setText("");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e("Bernoulli", "MBA onRequestPermissionsResult, SIZE : " + grantResults.length + ", Value " + grantResults[0]);

        switch (requestCode) {

            case REQUEST_CODE_CAMERA:
                button1();
                break;

            case REQUEST_CODE_FINE_LOCATION:
                button2();
                break;

            case REQUEST_CODE_RECORD_AUDIO:
                button3();
                break;
        }
    }
}
