package co.kruzr.bernoulli.desilting

import android.os.Build
import co.kruzr.bernoulli.Constants
import co.kruzr.bernoulli.CurrentScreen

/**
 * Helper class that works as starting point for asking for permissions if the PermissionDisabledPolicy for a
 * particular RequiresPermission annotation is PermissionDisabledPolicy.ASK_IF_MISSING.
 */
internal class AskPermissionSet(

        /**
         * Encapsulates the conditions associated with a stream's flow.
         */
        private val dam: Dam
) {

    /**
     * Start asking for permissions for a specific method
     */
    fun begin() {

        if (Build.VERSION.SDK_INT >= Constants.MIN_API_LEVEL_RUNTIME_PERMISSIONS)
            CurrentScreen.currentActivity?.let {

                it.askPermissions(
                        permissionStringArray,
                        dam.permissionRequestCode
                )
            }
    }

    /**
     * Returns an array of permissions in the format in which they are asked from the OS
     */
    private val permissionStringArray: Array<String?>
        get() {

            val permArray = arrayOfNulls<String>(dam.askPermissions.size)

            for (i in dam.askPermissions.indices)
                permArray[i] = dam.askPermissions[i].permission.permissionName

            return permArray
        }
}