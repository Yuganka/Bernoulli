package co.kruzr.bernoulli.desilting;

import android.os.Build;

import co.kruzr.bernoulli.Constants;
import co.kruzr.bernoulli.CurrentScreen;
import lombok.AllArgsConstructor;

/**
 * Helper class that works as starting point for asking for permissions if the PermissionDisabledPolicy for a
 * specific method is PermissionDisabledPolicy.ASK_IF_MISSING.
 */
@AllArgsConstructor
public class AskPermissionSet {

    /**
     * Encapsulates the conditions associated with a stream's flow.
     */
    private final Dam dam;

    /**
     * Start asking for permissions for a specific method
     */
    public void begin() {

        if (CurrentScreen.INSTANCE.getCurrentActivity() != null)

            if (Build.VERSION.SDK_INT >= Constants.MIN_API_LEVEL_RUNTIME_PERMISSIONS) {

                CurrentScreen.INSTANCE.getCurrentActivity().askPermissions(
                        getPermissionStringArray(),
                        dam.getPermissionRequestCode());
            }
    }

    private String[] getPermissionStringArray() {

        String[] permArray = new String[dam.getAskPermissions().size()];

        for (int i = 0; i < dam.getAskPermissions().size(); i++) {
            permArray[i] = dam.getAskPermissions().get(i).permission().getPermissionName();
        }

        return permArray;
    }
}
