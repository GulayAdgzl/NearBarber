package com.glyadgzl.nearbarber

import Banner
import BannerModel
import CategoryModel
import CategorySection
import DashboardRepository
import MyBottomBar
import TopBar
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.glyadgzl.nearbarber.Navigation.DashboardNavGraph
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class MainActivity : ComponentActivity() {

    private val uriState = MutableStateFlow("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            DashboardNavGraph(
                navController = navController,
                uriState = uriState
            )
        }
    }
}
@Composable
fun DashboardScreen(navController: NavController) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = colorResource(id = R.color.grey))

    val viewModel = DashboardRepository()
    val categories = remember { mutableListOf<CategoryModel>() }
    var showCategoryLoading by remember { mutableStateOf(true) }
    val banners = remember { mutableListOf<BannerModel>() }
    var showBannerLoading by remember { mutableStateOf(true) }

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.loadBanner().observeForever {
            banners.clear()
            banners.addAll(it)
            showBannerLoading = false
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadCategory().observeForever {
            categories.clear()
            categories.addAll(it)
            showCategoryLoading = false
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    Toast.makeText(context, "Chat butonuna tıklandı", Toast.LENGTH_SHORT).show()
                    // navController.navigate("chat_screen") gibi bir şey de yapılabilir
                },
                containerColor = Color.White,
                shape = CircleShape
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.btn_2),
                    contentDescription = "Chat",
                    tint = Color.Black
                )

            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.grey))
                .padding(paddingValues = innerPadding)
        ) {
            item { TopBar() }

            item { Banner(banners, showBannerLoading) }
            item { CategorySection(categories, showCategoryLoading) }

        }
    }
}
