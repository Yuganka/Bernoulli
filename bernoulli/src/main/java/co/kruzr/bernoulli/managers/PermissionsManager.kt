package co.kruzr.bernoulli.managers

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.content.ContextCompat
import co.kruzr.bernoulli.Constants
import co.kruzr.bernoulli.Permission
import co.kruzr.bernoulli.annotation.DangerousPermissionSince

/**
 * A class to overlook all permissions related work.
 */
internal class PermissionsManager constructor(private val context: Context) {

    /**
     * Checks whether a given permission has been granted.
     *
     * @param permission the Permission to check
     * @return whether the permission has been granted or not
     */
    fun isPermissionGranted(permission: Permission): Boolean {

        val deviceApiLevel = Build.VERSION.SDK_INT;

        Log.e("Bernoulli", "API Level - Permission : " + getPermissionApiLevel(permission) + ", App : " + deviceApiLevel);

        return if (getPermissionApiLevel(permission) <= deviceApiLevel)
            ContextCompat.checkSelfPermission(context, permission.permissionName) == PackageManager
                    .PERMISSION_GRANTED
        else
            true;
    }

    /**
     * Returns the Android API level in which this permission was added.
     */
    private fun getPermissionApiLevel(permission: Permission): Int {

        val dangerousPermissionSince : DangerousPermissionSince? = Permission::class.java
                .getField(permission.toString())
                .getAnnotation(DangerousPermissionSince::class.java);

        return dangerousPermissionSince?.apiLevel ?: Constants.MIN_API_LEVEL_RUNTIME_PERMISSIONS
    }
}