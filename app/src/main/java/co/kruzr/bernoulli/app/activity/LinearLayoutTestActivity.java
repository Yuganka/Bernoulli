/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package co.kruzr.bernoulli.app.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.List;

import co.kruzr.bernoulli.DisabledPolicy;
import co.kruzr.bernoulli.IFlowStateEvaluator;
import co.kruzr.bernoulli.Permission;
import co.kruzr.bernoulli.Settings;
import co.kruzr.bernoulli.annotation.RequiresSetting;
import co.kruzr.bernoulli.app.R;

/**
 *
 */
public class LinearLayoutTestActivity extends Activity implements IFlowStateEvaluator {

  private LinearLayout myLinearLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_linear_layout_test);

    myLinearLayout = (LinearLayout) findViewById(R.id.linearLayoutOne);
    myLinearLayout.invalidate();
    do1SettingWork();
  }

  @RequiresSetting(setting = Settings.GPS, disabledPolicy = DisabledPolicy.FAIL)
  @RequiresSetting(setting = Settings.AUTO_ROTATE, disabledPolicy = DisabledPolicy.PROCEED)
  private void do1SettingWork() {

    Log.e("Bernoulli", "successfully run 1 setting method");
  }

  @Override
  public void onFailure(List<Permission> missingPermissions, List<Settings> missingSettings) {

    Log.e("Bernoulli", "failure!");
  }
}
