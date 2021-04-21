package co.kruzr.bernoulli.android;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import co.kruzr.bernoulli.CurrentScreen;
import co.kruzr.bernoulli.annotation.AttachScreen;

/**
 * If the client wants to use PermissionDisabledPolicy.ASK_IF_MISSING with any RequiresPermission annotation, they will
 * only be able to use it from a sub-class of this activity.
 */
public class BernoulliActivity extends AppCompatActivity {

    @AttachScreen(isActive = true)
    @Override
    public void onResume() {
        super.onResume();
        Log.e("Bernoulli", "BA Hashcode " + hashCode());
        CurrentScreen.INSTANCE.setCurrentActivity(this);
        CurrentScreen.INSTANCE.setCurrentActivityHashcode(this.hashCode());
    }

    @AttachScreen(isActive = false)
    @Override
    public void onPause() {
        super.onPause();
        CurrentScreen.INSTANCE.setCurrentActivity(null);
        CurrentScreen.INSTANCE.setCurrentActivityHashcode(null);
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
