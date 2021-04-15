package co.kruzr.bernoulli.android

import co.kruzr.bernoulli.Permission

internal interface PermissionsInterface {

    fun onPermissionGranted(permission: Permission?)

    fun onPermissionDenied(permission: Permission?)

    fun onNeverShowAgainClicked(permission: Permission?)

    fun onPermissionRationalePositiveClick(permission: Permission?)

    fun onPermissionRationaleNegativeClick(permission: Permission?)

    fun onRedirectToSystemSettingsPositiveClick()

    fun onRedirectToSystemSettingsNegativeClick()

    fun onAllPermissionsGranted()
}