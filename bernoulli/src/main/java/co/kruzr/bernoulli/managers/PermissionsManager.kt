package co.kruzr.bernoulli.managers

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import co.kruzr.bernoulli.Permission

/**
 * A class to overlook all permissions related work.
 */
internal class PermissionsManager constructor(private val context: Context){

    /**
     * Checks whether a given permission has been granted.
     *
     * @param permission the Permission to check
     * @return whether the permission has been granted or not
     */
    fun isPermissionGranted(permission: Permission): Boolean {
        return ContextCompat.checkSelfPermission(context, permission.permissionName) == PackageManager
                .PERMISSION_GRANTED
    }
}