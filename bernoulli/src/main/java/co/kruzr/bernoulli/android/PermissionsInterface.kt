package co.kruzr.bernoulli.android

import co.kruzr.bernoulli.Permission
import co.kruzr.bernoulli.annotation.RequiresPermission

internal interface PermissionsInterface {

    fun onPermissionGranted(permission: RequiresPermission?)

    fun onPermissionDenied(permission: RequiresPermission?)

    fun onNeverShowAgainClicked(permission: RequiresPermission?)

    fun onPermissionRationalePositiveClick(permission: RequiresPermission?)

    fun onPermissionRationaleNegativeClick(permission: RequiresPermission?)

    fun onRedirectToSystemSettingsPositiveClick()

    fun onRedirectToSystemSettingsNegativeClick()

    fun onAllPermissionsGranted()
}