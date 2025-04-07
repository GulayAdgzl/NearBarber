package com.glyadgzl.nearbarber.Navigation

import ChatScreen
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.glyadgzl.nearbarber.ChatBot.ChatScreenWithTopBar
import com.glyadgzl.nearbarber.DashboardScreen
import com.glyadgzl.nearbarber.R
import kotlinx.coroutines.flow.MutableStateFlow
@Composable
fun DashboardNavGraph(
    navController: NavHostController,
    uriState: MutableStateFlow<String>
) {
    NavHost(
        navController = navController,
        startDestination = "dashboard"
    ) {
        composable("dashboard") {
            DashboardScreen(navController = navController)
        }


            composable("chat") {
                ChatScreenWithTopBar(navController = navController, uriState = remember { MutableStateFlow("") })
            }


    }

}
