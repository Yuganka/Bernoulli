package co.kruzr.bernoulli

/**
 * Enumerates various Android settings that the client may need.
 */
enum class Settings (

        /**
         * The description of the setting
         */
        internal val description: String = "") {

    GPS("GPS"),

    BLUETOOTH("Bluetooth"),

    AUTO_ROTATE("Auto Rotate"),

    INTERNET_ANY("Internet"),

    INTERNET_WIFI("Wi-Fi"),

    INTERNET_MOBILE_DATA("Mobile Internet"),

    AIRPLANE_MODE("Airplane Mode"),

    BATTERY_SAVER("Battery Saver"),

    DATA_ROAMING("Device Roaming");
}