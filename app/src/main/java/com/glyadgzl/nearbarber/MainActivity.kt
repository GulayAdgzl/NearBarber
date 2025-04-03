package com.glyadgzl.nearbarber

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.glyadgzl.nearbarber.ui.theme.NearBarberTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
         DashboardScreen()
            }
        }
    }
}

@Composable
@Preview
fun DashboardScreen() {
    val systemUiController=rememberSystemUiController()
    systemUiController.setStatusBarColor(color = colorResource(id=R.color.gray))
        
    val viewModel=DashboardRepository()
    val categories=remember{mutableStateOf( mutableListOf<CategoryModel>())}
    val showCategoryLoading by remember{mutableStateOf(value = true)}

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
                    .backgroundColor(color=colorResource(R.color.lightBlue))
                    .padding(paddingValues=innerPadding)
            ) {
                item{TopBar()}
                item{CategorySection(categories ,showCategoryLoading)}
            }
        }
    
}