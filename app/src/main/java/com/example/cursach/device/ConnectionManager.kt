package com.example.cursach.device

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import javax.inject.Inject

class ConnectionManager @Inject constructor(private val connectivityManager: ConnectivityManager) {
    val isConnected: Boolean
        get() {
            val currentNetwork = connectivityManager.activeNetwork
            val capabilities = connectivityManager.getNetworkCapabilities(currentNetwork)

            Log.d("kekw", "${capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)}")
            return capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false
        }
}
