package co.kruzr.bernoulli.android;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import co.kruzr.bernoulli.Permission;
import timber.log.Timber;


/**
 * A class to overlook all permissions related work.
 */
class PermissionsManager {

    private Context context;
    private PermissionsInterface permissionsInterface;

    public PermissionsManager(Context context, PermissionsInterface permissionsInterface) {
        this.context = context;
        this.permissionsInterface = permissionsInterface;
    }

    /**
     * Method to directly ask permission; can only be called from an activity
     *
     * @param permission the AppPermissionType which is to be asked
     */
    public void askPermission(Activity activity, Permission permission) {

        ActivityCompat.requestPermissions(activity, new String[]{permission.getPermissionName()}, permission.getRequestCode());
    }


    /**
     * Method to directly ask permission in fragment
     *
     * @param permission the AppPermissionType which is to be asked
     */
    public void askPermissionInFragment(Fragment fragment,
                                        Permission permission) {

        fragment.requestPermissions(new String[]{permission.getPermissionName()}, permission.getRequestCode());
    }


    public boolean isPermissionGranted(Permission permission) {

        boolean isGranted = ContextCompat.checkSelfPermission(context, permission.getPermissionName()) == PackageManager.PERMISSION_GRANTED;

        Timber.e("isGranted " + permission + " - " + isGranted);

        return isGranted;
    }

    /**
     * Shows the rationale for a given permission in a dialog box.
     *
     * @param permission the permission
     */
    @RequiresApi(23)
    public void showPermissionRationale(Permission permission) {

        new AlertDialog.Builder(context)
                .setTitle(permission.getSimpleName() + " Permission")
                .setMessage("Add some message bro")
                .setPositiveButton(android.R.string.ok,
                        (dialog, which) ->

                                permissionsInterface.onPermissionRationalePositiveClick(permission))
                .setNegativeButton(android.R.string.no, (dialog, which) -> {
                    permissionsInterface.onPermissionRationaleNegativeClick(permission);
                })
                .setIcon(android.R.drawable.ic_menu_call)
                .show();
    }


    /**
     * Redirects to the SETTINGS page when the user has clicked "Never show again" for a given
     * [AppPermission]
     *
     * @param permission the permission for which "Never show again" has been clicked
     */
    public void redirectToSettingsForPermission(Permission permission) {

        new AlertDialog.Builder(context)
                .setTitle(permission.getSimpleName() + " Permission")
                .setMessage("Please navigate to settings and provide the " + permission.getSimpleName() + " permission to ")
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> permissionsInterface.onRedirectToSystemSettingsPositiveClick())
                .setNegativeButton(android.R.string.no, (dialog, which) -> {

                    permissionsInterface.onRedirectToSystemSettingsNegativeClick();
                })
                .setIcon(android.R.drawable.ic_menu_help)
                .show();
    }

    /**
     * Getting a Permission from its request code.
     *
     * @param requestCode the requestCode
     * @return the Permission
     */
    public Permission getPermissionFromRequestCode(int requestCode) {

        for (Permission permission : Permission.values()) {
            if (permission.getRequestCode() == requestCode)
                return permission;
        }

        return null;
    }
}
