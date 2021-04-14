package co.kruzr.bernoulli;

import android.Manifest;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enumerates various Android Permissions that the client may need.
 */
@AllArgsConstructor
public enum Permission {

    FINE_LOCATION("Location", Manifest.permission.ACCESS_FINE_LOCATION, 11000),
    BACKGROUND_LOCATION("Background Location", Manifest.permission.ACCESS_BACKGROUND_LOCATION, 11001),
    GOOGLE_ACTIVITY_RECOGNITION("Activity Recognition", Manifest.permission.ACTIVITY_RECOGNITION, 11002),

    READ_CONTACTS("Contacts", Manifest.permission.READ_CONTACTS, 11003),
    RECORD_AUDIO("Microphone", Manifest.permission.RECORD_AUDIO, 11004),
    CALL_PHONE("Call Phone", Manifest.permission.CALL_PHONE, 11005),
    CAMERA("Camera", Manifest.permission.CAMERA, 11006),

    READ_CALL_LOG("Call Logs", Manifest.permission.READ_CALL_LOG, 11007),
    WRITE_EXTERNAL_STORAGE("Storage", Manifest.permission.WRITE_EXTERNAL_STORAGE, 11008);

    @Getter
    private final String simpleName;

    @Getter
    private final String permissionName;

    @Getter
    private final int requestCode;

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
