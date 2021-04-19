package co.kruzr.bernoulli;

import android.Manifest;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enumerates various Android Permissions that the client may need.
 */
@AllArgsConstructor
public enum Permission {

    FINE_LOCATION("Location", Manifest.permission.ACCESS_FINE_LOCATION),
    BACKGROUND_LOCATION("Background Location", Manifest.permission.ACCESS_BACKGROUND_LOCATION),
    GOOGLE_ACTIVITY_RECOGNITION("Activity Recognition", Manifest.permission.ACTIVITY_RECOGNITION),

    READ_CONTACTS("Contacts", Manifest.permission.READ_CONTACTS),
    RECORD_AUDIO("Microphone", Manifest.permission.RECORD_AUDIO),
    CALL_PHONE("Call Phone", Manifest.permission.CALL_PHONE),
    CAMERA("Camera", Manifest.permission.CAMERA),

    READ_CALL_LOG("Call Logs", Manifest.permission.READ_CALL_LOG),
    WRITE_EXTERNAL_STORAGE("Storage", Manifest.permission.WRITE_EXTERNAL_STORAGE);

    @Getter
    public final String simpleName;

    @Getter
    public final String permissionName;
}
