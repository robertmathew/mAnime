package org.robert.project.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.SnackbarHostState
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.robert.project.utils.ConnectivityService
import org.robert.project.utils.RealConnectivityService

class MainActivity : ComponentActivity() {

    lateinit var connectivityService: ConnectivityService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        connectivityService = RealConnectivityService(context = applicationContext)

        setContent {
            Navigation(networkMonitor = connectivityService)
        }


    }
}
