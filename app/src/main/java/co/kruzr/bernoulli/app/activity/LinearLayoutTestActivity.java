/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package co.kruzr.bernoulli.app.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import co.kruzr.bernoulli.DisabledPolicy;
import co.kruzr.bernoulli.Settings;
import co.kruzr.bernoulli.annotation.RequiresSetting;
import co.kruzr.bernoulli.app.R;

/**
 *
 */
public class LinearLayoutTestActivity extends Activity {

  private LinearLayout myLinearLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_linear_layout_test);

    myLinearLayout = (LinearLayout) findViewById(R.id.linearLayoutOne);
    myLinearLayout.invalidate();
    do1SettingWork();
    Log.e("Bernoulli", "post work");
  }

  @RequiresSetting(setting = Settings.GPS, disabledPolicy = DisabledPolicy.FAIL)
  private void do1SettingWork() {

    Log.e("Bernoulli", "successfully run 1 setting method");
  }
}
