package co.kruzr.bernoulli;

import android.Manifest;

/**
 * Enumerates various Android Permissions that the client may need.
 */
public enum Permission {

    LOCATION("Location", Manifest.permission.ACCESS_FINE_LOCATION, 11000),
    BACKGROUND_LOCATION("Background Location", Manifest.permission.ACCESS_BACKGROUND_LOCATION, 11001),
    GOOGLE_ACTIVITY_RECOGNITION("Activity Recognition", Manifest.permission.ACTIVITY_RECOGNITION, 11002),

    READ_CONTACTS("Contacts", Manifest.permission.READ_CONTACTS, 11003),
    RECORD_AUDIO("Microphone", Manifest.permission.RECORD_AUDIO, 11004),
    CALL_PHONE("Call Phone", Manifest.permission.CALL_PHONE, 11005),

    READ_CALL_LOG("Call Logs", Manifest.permission.READ_CALL_LOG, 11006),
    WRITE_EXTERNAL_STORAGE("Storage", Manifest.permission.WRITE_EXTERNAL_STORAGE, 11007);

    private String simpleName;
    private String permissionName;
    private int requestCode;

    Permission(String simpleName, String permissionName, int requestCode) {
        this.simpleName = simpleName;
        this.permissionName = permissionName;
        this.requestCode = requestCode;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public int getRequestCode() {
        return requestCode;
    }

    /**
     * REQUEST CODES
     */
    public static final int REQUEST_CODE_SETTINGS = 3243;

    public static final int REQUEST_CODE_PERMISSION_ACCESS_FINE_LOCATION = 2085;
    public static final int REQUEST_CODE_PERMISSION_ACTIVITY_RECOGNITION = 2086;

    public static final int REQUEST_CODE_PERMISSION_READ_CONTACTS = 2087;
    public static final int REQUEST_CODE_PERMISSION_RECORD_AUDIO = 2088;
    public static final int REQUEST_CODE_PERMISSION_CALL_PHONE = 2089;
    public static final int REQUEST_CODE_PERMISSION_CALL_LOGS = 2090;
    public static final int REQUEST_CODE_PERMISSION_WRITE_EXTERNAL_STORAGE = 2091;
    public static final int REQUEST_CODE_PERMISSION_ACCESS_BACKGROUND_LOCATION = 2092;
}
