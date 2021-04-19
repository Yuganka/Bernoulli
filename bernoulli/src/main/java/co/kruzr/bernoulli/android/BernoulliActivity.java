package co.kruzr.bernoulli.android;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import co.kruzr.bernoulli.CurrentScreen;
import co.kruzr.bernoulli.annotation.AttachScreen;

public class BernoulliActivity extends AppCompatActivity {

    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AttachScreen(isActive = true)
    @Override
    public void onResume() {
        super.onResume();
        Log.e("Bernoulli", "BA Hashcode " + hashCode());
        CurrentScreen.setCurrentActivity(this);
        CurrentScreen.setCurrentActivityHashcode(this.hashCode());
    }

    @AttachScreen(isActive = false)
    @Override
    public void onPause() {
        super.onPause();
        CurrentScreen.setCurrentActivity(null);
        CurrentScreen.setCurrentActivityHashcode(null);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        Log.e("Bernoulli", "BA onRequestPermissionsResult");
    }

    public void askPermissions(String[] permissionStringArray, Integer requestCode) {

        ActivityCompat.requestPermissions(
                this,
                permissionStringArray,
                requestCode
        );
    }
}
