package com.glyadgzl.nearbarber.Results

import CategoryModel
import NearestSection
import PopularSection
import ResultsViewModel
import Search
import StoreModel
import SubCategorySection
import TopTitle
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage


import com.glyadgzl.nearbarber.R

class ResultsActivity : AppCompatActivity() {
    private var id: String = ""
    private var title: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        id=intent.getStringExtra("id")?:""
        title=intent.getStringExtra("title")?:""

        setContent {
            ResultList(id,title,onBackClick={finish()})
        }
    }
}

@Composable
@Preview
fun ResultList(id: String="1", title: String="", onBackClick: () -> Unit={}) {


    val viewModel = ResultsViewModel()

val subCategory = remember { mutableStateListOf<CategoryModel>() }

var showsubCategoryLoading by remember { mutableStateOf( true) }

LaunchedEffect (Unit) {
    viewModel.loadSubCategory(id).observeForever {
        subCategory.clear()
        subCategory.addAll(it)
        showsubCategoryLoading = false
    }
}


val popular = remember { mutableStateListOf<StoreModel>() }

var showPopularLoading by remember { mutableStateOf( true) }

LaunchedEffect (Unit) {
    viewModel.loadPopular(id).observeForever {
        popular.clear()
        popular.addAll(it)
        showPopularLoading = false
    }
}
val nearest= remember { mutableStateListOf<StoreModel>() }

var showNearestLoading by remember { mutableStateOf(true) }

LaunchedEffect (Unit) {
    viewModel.loadNearest(id).observeForever {
        nearest.clear()
        nearest.addAll(it)
        showNearestLoading = false
    }
}

    LazyColumn (
        modifier=Modifier.fillMaxSize().
        background(color = colorResource(R.color.grey))
    ){ 
        item{TopTitle(title,onBackClick)}
        item{
            Search()
        }
        item{SubCategorySection(subCategory,showsubCategoryLoading)
            //SubCategorySection(subCategory,showsubCategoryLoading)
        }
        item{PopularSection(popular,showPopularLoading)
            //PopularSection(popular,showPopularLoading)
        }
        item{NearestSection(nearest,showNearestLoading)}
    }
}

