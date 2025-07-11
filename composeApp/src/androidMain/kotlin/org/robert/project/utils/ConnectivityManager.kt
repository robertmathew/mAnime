package org.robert.project.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.NET_CAPABILITY_VALIDATED
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged

interface ConnectivityService {
    fun isConnectionAvailable(): Flow<Boolean>
}

class RealConnectivityService(
    private val context: Context,
) : ConnectivityService {

    private val connectivityManager = context
        .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun isConnectionAvailable(): Flow<Boolean> = callbackFlow {
        val callback = object : ConnectivityManager.NetworkCallback() {

            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities,
            ) {
                super.onCapabilitiesChanged(network, networkCapabilities)

                val connected = networkCapabilities.hasCapability(NET_CAPABILITY_VALIDATED) // 1.
                trySend(connected)
            }

            override fun onUnavailable() {
                super.onUnavailable()
                trySend(false)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                trySend(false)
            }

            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                val capabilities = connectivityManager.getNetworkCapabilities(network)
                trySend(capabilities?.hasCapability(NET_CAPABILITY_VALIDATED) == true)
            }
        }

        connectivityManager.registerDefaultNetworkCallback(callback) // 2.

        val initialNetwork = connectivityManager.activeNetwork
        if (initialNetwork != null) {
            val capabilities = connectivityManager.getNetworkCapabilities(initialNetwork)
            trySend(capabilities?.hasCapability(NET_CAPABILITY_VALIDATED) == true)
        } else {
            trySend(false)
        }

        awaitClose { // 3.
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }.distinctUntilChanged() // 4.
}