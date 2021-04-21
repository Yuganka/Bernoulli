package co.kruzr.bernoulli.desilting;

import android.os.Build;

import java.util.List;

import co.kruzr.bernoulli.CurrentScreen;
import co.kruzr.bernoulli.annotation.RequiresPermission;
import lombok.AllArgsConstructor;

/**
 * Helper class that works as starting point for asking for permissions if the PermissionDisabledPolicy for a
 * specific method is PermissionDisabledPolicy.ASK_IF_MISSING.
 */
@AllArgsConstructor
public class AskPermissionSet {

    /**
     * The list of permissions required by a specific method.
     */
    private final List<RequiresPermission> requiresPermission;

    /**
     * Start asking for permissions for a specific method
     */
    public void begin() {

        if (CurrentScreen.INSTANCE.getCurrentActivity() != null)

            if (Build.VERSION.SDK_INT >= 23) {

                CurrentScreen.INSTANCE.getCurrentActivity().askPermissions(
                        new String[]{requiresPermission.get(0).permission().getPermissionName()},
                        requiresPermission.get(0).permissionRequestCode());
            }
    }

    private String[] getPermissionStringArray() {

        String[] permArray = new String[requiresPermission.size()];

        for (int i = 0; i < requiresPermission.size(); i++) {
            permArray[i] = requiresPermission.get(i).permission().getPermissionName();
        }

        return permArray;
    }
}
