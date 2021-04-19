package co.kruzr.bernoulli.desilting;

import android.os.Build;

import java.util.List;

import co.kruzr.bernoulli.CurrentScreen;
import co.kruzr.bernoulli.annotation.RequiresPermission;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AskPermissionSet {

    private final List<RequiresPermission> requiresPermission;

    // start asking for permissions for a specific method
    public void begin() {

        if (CurrentScreen.getCurrentActivity() != null)

            if (Build.VERSION.SDK_INT >= 23) {

                CurrentScreen.getCurrentActivity().askPermissions(
                        new String[]{requiresPermission.get(0).permission().getPermissionName()},
                        requiresPermission.get(0).permissionRequestCode());

                CurrentScreen.setCurrentlyProcessingPermission(requiresPermission.get(0));
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
