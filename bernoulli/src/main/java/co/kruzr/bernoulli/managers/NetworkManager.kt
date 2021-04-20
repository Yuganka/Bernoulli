package co.kruzr.bernoulli.managers

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class NetworkManager constructor(private val context: Context){

    private val connectivityManager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private var networkInfo: NetworkInfo? = null

    val isInternetAvailable: Boolean
        get() = connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!.isConnectedOrConnecting

    val isWifiAvailable: Boolean
        get() {
            networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
            return if (networkInfo != null) networkInfo!!.isConnected else false
        }

    val isMobileInternetAvailable: Boolean
        get() {
            networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
            return if (networkInfo != null) networkInfo!!.isConnected else false
        }
}