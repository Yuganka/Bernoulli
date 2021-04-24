package co.kruzr.bernoulli;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enumerates various Android settings that the client may need.
 */
@AllArgsConstructor
public enum Settings {

    GPS("GPS"),

    BLUETOOTH("Bluetooth"),

    AUTO_ROTATE("Auto Rotate"),

    INTERNET_ANY("Internet"),

    INTERNET_WIFI("Wi-Fi"),

    INTERNET_MOBILE_DATA("Mobile Internet"),

    AIRPLANE_MODE("Airplane Mode"),

    BATTERY_SAVER("Battery Saver"),

    DATA_ROAMING("Device Roaming");

    /**
     * The description of the setting
     */
    @Getter(AccessLevel.MODULE)
    public final String description;
}
