package co.kruzr.bernoulli.desilting;

import android.os.Build;

import androidx.core.app.ActivityCompat;

import co.kruzr.bernoulli.CurrentScreen;
import co.kruzr.bernoulli.Permission;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AskPermission {

    private final Permission permission;

    public void begin() {

        if (CurrentScreen.getCurrentActivity() != null)

            if (Build.VERSION.SDK_INT >= 23) {

                ActivityCompat.requestPermissions
                        (
                                CurrentScreen.getCurrentActivity(),
                                new String[]{permission.getPermissionName()},
                                permission.getRequestCode()
                        );
            }
    }
}
