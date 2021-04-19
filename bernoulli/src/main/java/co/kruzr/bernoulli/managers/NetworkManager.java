package co.kruzr.bernoulli.managers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import co.kruzr.bernoulli.BernoulliBank;

public class NetworkManager {

    private final ConnectivityManager connectivityManager;
    private NetworkInfo networkInfo;

    public NetworkManager() {

        connectivityManager =
                (ConnectivityManager) BernoulliBank.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public boolean isInternetAvailable() {
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }


    public boolean isWifiAvailable() {

        networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (networkInfo != null)
            return networkInfo.isConnected();
        else
            return false;
    }

    public boolean isMobileInternetAvailable() {

        networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (networkInfo != null)
            return networkInfo.isConnected();
        else
            return false;
    }
}
