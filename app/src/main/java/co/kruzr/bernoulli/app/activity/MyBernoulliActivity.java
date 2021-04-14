package co.kruzr.bernoulli.app.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.List;

import co.kruzr.bernoulli.DisabledPolicy;
import co.kruzr.bernoulli.Permission;
import co.kruzr.bernoulli.Settings;
import co.kruzr.bernoulli.android.BernoulliActivity;
import co.kruzr.bernoulli.annotation.RequiresPermission;
import co.kruzr.bernoulli.annotation.RequiresSetting;
import co.kruzr.bernoulli.app.R;

public class MyBernoulliActivity extends BernoulliActivity implements View.OnClickListener {

    private TextView textviewLogs;
    private Button button1Perm, button2Perm;
    private Button button1Setting, button2Setting;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bernoulli);

        textviewLogs = findViewById(R.id.textview_logs);

        button1Perm = findViewById(R.id.button_perm_1);
        button1Setting = findViewById(R.id.button_setting_1);
        button2Perm = findViewById(R.id.button_perm_2);
        button2Setting = findViewById(R.id.button_setting_2);
    }

    @Override
    protected void onStart() {
        super.onStart();

        button1Perm.setOnClickListener(this);
        button1Setting.setOnClickListener(this);
        button2Perm.setOnClickListener(this);
        button2Setting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button_perm_1:
                perm1Proceed();
                break;

            case R.id.button_perm_2:
                perm1Fail();
                break;

            case R.id.button_setting_1:
                setting1Proceed();
                break;

            case R.id.button_setting_2:
                setting2Fail();
                break;
        }
    }

    @RequiresPermission(permission = Permission.READ_CONTACTS, disabledPolicy = DisabledPolicy.PROCEED)
    private void perm1Proceed() {

        textviewLogs.setText("successfully run perm contact proceed");
        Log.e("Bernoulli", "successfully run perm contact proceed");
    }

    @RequiresPermission(permission = Permission.FINE_LOCATION, disabledPolicy = DisabledPolicy.FAIL)
    private void perm1Fail() {

        textviewLogs.setText("successfully run perm location fail");

        Log.e("Bernoulli", "successfully run perm location fail");
    }

    @RequiresSetting(setting = Settings.GPS, disabledPolicy = DisabledPolicy.PROCEED)
    private void setting1Proceed() {

        textviewLogs.setText("successfully run setting GPS proceed");

        Log.e("Bernoulli", "successfully run setting GPS proceed");
    }

    @RequiresSetting(setting = Settings.AUTO_ROTATE, disabledPolicy = DisabledPolicy.FAIL)
    private void setting2Fail() {

        textviewLogs.setText("successfully run setting AutoRotate fail");

        Log.e("Bernoulli", "successfully run setting AutoRotate fail");
    }

    private String getCombinedString(List<Permission> listMissingPerms,
                                     List<Settings> listMissingSettings) {

        StringBuilder combined = new StringBuilder();

        combined.append("Permissions - ").append("\n");

        for (Permission perm : listMissingPerms)
            combined.append(perm).append("\n");

        combined.append("\nSettings\n");

        for (Settings setting : listMissingSettings)
            combined.append(setting).append("\n");

        return combined.toString();

    }
}
