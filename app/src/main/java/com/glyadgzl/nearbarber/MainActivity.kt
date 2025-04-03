package com.glyadgzl.nearbarber

import Banner
import BannerModel
import CategoryModel
import CategorySection
import DashboardRepository
import MyBottomBar
import TopBar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
         DashboardScreen()
            }

    }
}

@Composable
@Preview
fun DashboardScreen() {
    val systemUiController=rememberSystemUiController()
    systemUiController.setStatusBarColor(color = colorResource(id=R.color.grey))
        
    val viewModel=DashboardRepository()
    val categories=remember{ mutableListOf<CategoryModel>()}
    var showCategoryLoading by remember{mutableStateOf(value = true)}
    val banners=remember{ mutableListOf<BannerModel>()}
    var showBannerLoading by remember{mutableStateOf(value = true)}
    LaunchedEffect(Unit){
        viewModel.loadBanner().observeForever{
            banners.clear()
            banners.addAll(it)
            showBannerLoading=false

        }
    }
    LaunchedEffect(Unit){
        viewModel.loadCategory().observeForever{
            categories.clear()
            categories.addAll(it)
            showCategoryLoading=false

        }
    }
    
    
    Scaffold(
            
            bottomBar = { MyBottomBar() }
        ) { innerPadding ->
           LazyColumn(
                modifier = Modifier
                    .fillMaxSize()

                    .background(color=colorResource(R.color.grey))
                    .padding(paddingValues=innerPadding)
            ) {
                item{TopBar()}
                item{CategorySection(categories ,showCategoryLoading)}
                item{Banner(banners ,showBannerLoading)}
            }
        }
    
}
