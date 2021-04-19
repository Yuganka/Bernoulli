package co.kruzr.bernoulli;

import android.util.Log;

import java.lang.annotation.Annotation;
import java.util.List;

import co.kruzr.bernoulli.annotation.RequiresPermission;
import co.kruzr.bernoulli.annotation.RequiresSetting;
import co.kruzr.bernoulli.desilting.Dam;

/**
 * Class to simplify printing.
 */
class PrintUtils {

    /**
     * To print the permission and setting requirements of a method.
     */
    public static String printRequiredPermissionsAndSettings(Stream stream) {

        StringBuilder combined = new StringBuilder();

        combined.append("Permissions - ").append("\n");

        for (RequiresPermission perm : stream.getRequiredPermissions())
            combined.append(perm.permission() + ", disabledPolicy " + perm.permissionDisabledPolicy()).append("\n");

        combined.append("\nSettings\n");

        for (RequiresSetting setting : stream.getRequiredSettings())
            combined.append(setting.setting() + ", settingsStateMismatchPolicy " + setting.settingsStateMismatchPolicy()).append("\n");

        return combined.toString();

    }

    /**
     * To print the list of currently missing permissions and settings for the method contained in the
     * evaluatedStream object.
     *
     * @param dam containing details of the obstructions in the stream (i.e. method)
     * @return stringified missing permissions and settings
     */
    public static String printMissingPermissionsAndSettings(Dam dam) {

        StringBuilder combined = new StringBuilder();

        combined.append("Permissions - ").append("\n");

        for (RequiresPermission perm : dam.getAskPermissions())
            combined.append(perm.permission()).append("\n");

        combined.append("\nSettings\n");

        for (RequiresSetting setting : dam.getShowSettingsRequirementDialog())
            combined.append(setting).append("\n");

        return combined.toString();
    }

    /**
     * To print the annotations of a method.
     */
    public static void printMethodAnnotations(List<Annotation> annotationList) {

        Log.e("Bernoulli", "Printing annotations, Size - " + annotationList.size());

        for (Annotation annotation : annotationList)
            Log.e("Bernoulli", annotation.toString());
    }

    /**
     * To print details of the current activity.
     */
    public static void printCurrentActivityDetails() {

        Log.e("Bernoulli", "Current Activity - " + CurrentScreen.getCurrentActivity());
        Log.e("Bernoulli", "Current Activity Hashcode - " + CurrentScreen.getCurrentActivityHashcode());
    }

    private String getCombinedString(List<Permission> listMissingPerms,
                                     List<Settings> listMissingSettings) {

        StringBuilder combined = new StringBuilder();

        combined.append("Permissions - ").append("\n");

        for (Permission perm : listMissingPerms)
            combined.append(perm).append("\n");

        combined.append("\nSettings\n");

        for (Settings setting : listMissingSettings)
            combined.append(setting).append("\n");

        return combined.toString();

    }
}
