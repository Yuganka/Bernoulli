package co.kruzr.bernoulli.desilting;

import android.os.Build;

import java.util.List;

import co.kruzr.bernoulli.CurrentScreen;
import co.kruzr.bernoulli.PermissionResolution;
import co.kruzr.bernoulli.android.PermissionsManager;
import co.kruzr.bernoulli.annotation.RequiresPermission;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AskPermissionSet {

    private final List<RequiresPermission> listPermissions;

    public PermissionResolution begin() {

        if (CurrentScreen.getCurrentActivity() != null)

            if (Build.VERSION.SDK_INT >= 23)
                CurrentScreen.getCurrentActivity().askPermissions(
                        getPermissionStringArray(),
                        PermissionsManager.REQUEST_CODE);

    }

    private String[] getPermissionStringArray() {

        String[] permArray = new String[listPermissions.size()];

        for (int i = 0; i < listPermissions.size(); i++) {
            permArray[i] = listPermissions.get(i).permission().getPermissionName();
        }

        return permArray;
    }
}
