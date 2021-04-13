package co.kruzr.bernoulli;

import co.kruzr.bernoulli.annotation.RequiresPermission;
import co.kruzr.bernoulli.annotation.RequiresSetting;

/**
 * Class to simplify printing.
 */
class PrintUtils {

    /**
     * To print the permission and setting requirements of a method.
     */
    public static String printRequiredPermissionsAndSettings(Stream stream){

        StringBuilder combined = new StringBuilder();

        combined.append("Permissions - ").append("\n");

        for (RequiresPermission perm : stream.getRequiredPermissions())
            combined.append(perm.permission() + ", disabledPolicy " + perm.disabledPolicy()).append("\n");

        combined.append("\nSettings\n");

        for (RequiresSetting setting : stream.getRequiredSettings())
            combined.append(setting.setting() + ", disabledPolicy " + setting.disabledPolicy()).append("\n");

        return combined.toString();

    }

    /**
     * To print the list of currently missing permissions and settings for the method contained in the
     * evaluatedStream object.
     * @param evaluatedStream  containing details of the stream (i.e. method)
     * @return                 stringified missing permissions and settings
     */
    public static String printMissingPermissionsAndSettings(EvaluatedStream evaluatedStream){

        StringBuilder combined = new StringBuilder();

        combined.append("Permissions - ").append("\n");

        for (Permission perm : evaluatedStream.getMissingPermissions())
            combined.append(perm).append("\n");

        combined.append("\nSettings\n");

        for (Settings setting : evaluatedStream.getMissingSettings())
            combined.append(setting).append("\n");

        return combined.toString();

    }
}
