package co.kruzr.bernoulli.android;

import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import co.kruzr.bernoulli.Permission;
import timber.log.Timber;

public class BernoulliActivity extends AppCompatActivity implements PermissionsInterface {

    private PermissionsManager permissionsManager;

    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        permissionsManager = new PermissionsManager(this, this);
    }

    @Override
    @RequiresApi(23)
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        Timber.e("onRequestPermissionsResult " + permissions);

        Permission permission = permissionsManager.getPermissionFromRequestCode(requestCode);

        if (permission != null) {

            if (grantResults.length > 0) {

                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {

                    if (shouldShowRequestPermissionRationale(permission.getPermissionName()))
                        onPermissionDenied(permission);
                    else
                        onNeverShowAgainClicked(permission);

                } else
                    onPermissionGranted(permission);
            }
        }
    }


    @Override
    public final void onPermissionGranted(Permission permission) {

    }

    @Override
    public void onPermissionDenied(Permission permission) {

    }

    @Override
    public void onNeverShowAgainClicked(Permission permission) {

    }

    @Override
    public void onPermissionRationalePositiveClick(Permission permission) {

    }

    @Override
    public void onPermissionRationaleNegativeClick(Permission permission) {

    }

    @Override
    public void onRedirectToSystemSettingsPositiveClick() {

    }

    @Override
    public void onRedirectToSystemSettingsNegativeClick() {

    }

    @Override
    public void onAllPermissionsGranted() {

    }
}
