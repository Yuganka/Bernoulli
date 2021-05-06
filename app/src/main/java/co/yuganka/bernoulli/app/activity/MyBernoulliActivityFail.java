package co.yuganka.bernoulli.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import co.yuganka.bernoulli.Permission;
import co.yuganka.bernoulli.PermissionDisabledPolicy;
import co.yuganka.bernoulli.Settings;
import co.yuganka.bernoulli.SettingsStateMismatchPolicy;
import co.yuganka.bernoulli.android.BernoulliActivity;
import co.yuganka.bernoulli.annotation.RequiresPermission;
import co.yuganka.bernoulli.annotation.RequiresSetting;
import co.yuganka.bernoulli.app.R;

/**
 * An activity to test all combinations of a fixed PermissionDisabledPolicy value of
 * PermissionDisabledPolicy.FAIL and all possible values of SettingsStateMismatchPolicy.
 */
public class MyBernoulliActivityFail extends BernoulliActivity implements View.OnClickListener {

    private TextView textviewLogs;
    private Button button1, button2, button3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bernoulli_fail);

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
                startActivity(new Intent(MyBernoulliActivityFail.this, MyBernoulliActivityAsk.class));
            }
        });
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
            PermissionDisabledPolicy.FAIL)
    @RequiresSetting(setting = Settings.GPS, shouldBeEnabled = true, settingsStateMismatchPolicy =
            SettingsStateMismatchPolicy.PROCEED)
    private void button1() {

        textviewLogs.setText("Run button 1");
    }

    @RequiresPermission(permission = Permission.ACCESS_FINE_LOCATION, permissionDisabledPolicy =
            PermissionDisabledPolicy.FAIL)
    @RequiresSetting(setting = Settings.GPS, shouldBeEnabled = true, settingsStateMismatchPolicy =
            SettingsStateMismatchPolicy.FAIL)
    private void button2() {

        textviewLogs.setText("Run button 2");
    }

    @RequiresPermission(permission = Permission.RECORD_AUDIO, permissionDisabledPolicy =
            PermissionDisabledPolicy.FAIL)
    @RequiresSetting(setting = Settings.GPS, shouldBeEnabled = true, settingsStateMismatchPolicy =
            SettingsStateMismatchPolicy.SHOW_DIALOG)
    private void button3() {

        textviewLogs.setText("Run button 3");
    }

    private void clearTextViewLogs() {
        textviewLogs.setText("");
    }

    /**
     * Since we won't be asking for permissions in any of the cases in this activity, no callback to this method
     * shall come.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
