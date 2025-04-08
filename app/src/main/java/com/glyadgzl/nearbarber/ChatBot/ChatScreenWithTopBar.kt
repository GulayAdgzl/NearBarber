package com.glyadgzl.nearbarber.ChatBot

import ChatScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.glyadgzl.nearbarber.R
import kotlinx.coroutines.flow.MutableStateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun ChatScreenWithTopBar(
    navController: NavController, // Geri butonu için
    uriState: MutableStateFlow<String>
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Chat with AI Bot",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Gray
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Gray
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* Profile Icon action */ }) {
                       Image(
                            painter = painterResource(R.drawable.profile), // Profile image
                            contentDescription = "Profile",
                            modifier = Modifier.size(36.dp) // Profile image boyutu
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White // Koyu arka plan
                )
            )
        }
    ) { paddingValues ->
        ChatScreen(
            paddingValues = paddingValues,
            uriState = uriState
        )
    }
}
@Preview(showBackground = true)
@Composable
fun ChatScreenWithTopBarPreview() {
    val navController = rememberNavController()
    val uriState = remember { MutableStateFlow("") }

    ChatScreenWithTopBar(navController = navController, uriState = uriState)
}
