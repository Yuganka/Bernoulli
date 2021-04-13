package co.kruzr.bernoulli.android;

import co.kruzr.bernoulli.Permission;

interface PermissionsInterface {

    void onPermissionGranted(Permission permission);

    void onPermissionDenied(Permission permission);

    void onNeverShowAgainClicked(Permission permission);

    void onPermissionRationalePositiveClick(Permission permission);

    void onPermissionRationaleNegativeClick(Permission permission);

    void onRedirectToSystemSettingsPositiveClick();

    void onRedirectToSystemSettingsNegativeClick();

    void onAllPermissionsGranted();
}
