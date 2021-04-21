package co.kruzr.bernoulli.managers

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * A helper class to perform network related checks.
 */
internal class NetworkManager constructor(private val context: Context){

    private val connectivityManager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private var networkInfo: NetworkInfo? = null

    /**
     * @return true if internet connection (any kind) is available, false otherwise
     */
    val isInternetAvailable: Boolean
        get() = connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!.isConnectedOrConnecting

    /**
     * @return true if WiFi connection is available, false otherwise
     */
    val isWifiAvailable: Boolean
        get() {
            networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
            return if (networkInfo != null) networkInfo!!.isConnected else false
        }

    /**
     * @return true if mobile data connection is available, false otherwise
     */
    val isMobileInternetAvailable: Boolean
        get() {
            networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
            return if (networkInfo != null) networkInfo!!.isConnected else false
        }

    /**
     * @return true if device is in roaming state, false otherwise
     */
    val isDeviceInRoamingState: Boolean
        get() {
            networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
            return if (networkInfo != null) networkInfo!!.isRoaming else false
        }
}