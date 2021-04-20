package co.kruzr.bernoulli;

import android.Manifest;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enumerates various Android Permissions that the client may need.
 */
@AllArgsConstructor
public enum Permission {

    ACCEPT_HANDOVER("Accept Handover", Manifest.permission.ACCEPT_HANDOVER),

    ACCESS_BACKGROUND_LOCATION("Background Location", Manifest.permission.ACCESS_BACKGROUND_LOCATION),

    ACCESS_COARSE_LOCATION("Coarse Location", Manifest.permission.ACCESS_COARSE_LOCATION),

    ACCESS_FINE_LOCATION("Fine Location", Manifest.permission.ACCESS_FINE_LOCATION),

    ACCESS_MEDIA_LOCATION("Access Media Location", Manifest.permission.ACCESS_MEDIA_LOCATION),

    ADD_VOICEMAIL("Add Voicemail", Manifest.permission.ADD_VOICEMAIL),

    ANSWER_PHONE_CALLS("Answer Phone Calls", Manifest.permission.ANSWER_PHONE_CALLS),

    BODY_SENSORS("Body Sensors", Manifest.permission.BODY_SENSORS),

    CALL_PHONE("Call Phone", Manifest.permission.CALL_PHONE),

    CAMERA("Camera", Manifest.permission.CAMERA),

    GET_ACCOUNTS("Get Accounts", Manifest.permission.GET_ACCOUNTS),

    PROCESS_OUTGOING_CALLS("Process Outgoing Calls", Manifest.permission.PROCESS_OUTGOING_CALLS),

    RECORD_AUDIO("Microphone", Manifest.permission.RECORD_AUDIO),

    READ_CALL_LOG("Read Call Log", Manifest.permission.READ_CALL_LOG),

    READ_CALENDAR("Read Calendar", Manifest.permission.READ_CALENDAR),

    READ_CONTACTS("Contacts", Manifest.permission.READ_CONTACTS),

    READ_EXTERNAL_STORAGE("Read External Storage", Manifest.permission.READ_EXTERNAL_STORAGE),

    READ_PHONE_NUMBERS("Read Phone Numbers", Manifest.permission.READ_PHONE_NUMBERS),

    READ_PHONE_STATE("Read Phone State", Manifest.permission.READ_PHONE_STATE),

    READ_SMS("Read SMS", Manifest.permission.READ_SMS),

    RECEIVE_MMS("Receive MMS", Manifest.permission.RECEIVE_MMS),

    RECEIVE_SMS("Receive SMS", Manifest.permission.RECEIVE_SMS),

    RECEIVE_WAP_PUSH("Receive WAP Push", Manifest.permission.RECEIVE_WAP_PUSH),

    SEND_SMS("Send SMS", Manifest.permission.SEND_SMS),

    USE_SIP("Use SIP Service", Manifest.permission.USE_SIP),

    WRITE_CALENDAR("Write Calendar", Manifest.permission.WRITE_CALENDAR),

    WRITE_CALL_LOG("Write Call Log", Manifest.permission.WRITE_CALL_LOG),

    WRITE_CONTACTS("Write Contacts", Manifest.permission.WRITE_CONTACTS),

    WRITE_EXTERNALS_STORAGE("Write External Storage", Manifest.permission.WRITE_EXTERNAL_STORAGE),

    ACTIVITY_RECOGNITION("Activity Recognition", Manifest.permission.ACTIVITY_RECOGNITION);

    @Getter
    public final String simpleName;

    @Getter
    public final String permissionName;
}
