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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource



import com.glyadgzl.nearbarber.R

class ResultsActivity : AppCompatActivity() {
    private var id: String = ""
    private var title: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // id ve title verilerini Intent'ten alıyoruz
        val intId = intent.getIntExtra("id", -1)
        id = intId.toString()

        title = intent.getStringExtra("title") ?: ""

        setContent {
            ResultList(id, title, onBackClick = { finish() })
        }
    }
}

@Composable
@Preview
fun ResultList(id: String = "1", title: String = "", onBackClick: () -> Unit = {}) {
    val viewModel = ResultsViewModel()

    val subCategory = remember { mutableStateListOf<CategoryModel>() }
    var showSubCategoryLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        viewModel.loadSubCategory(id).observeForever {
            subCategory.clear()
            subCategory.addAll(it)
            showSubCategoryLoading = false
        }
    }

    val popular = remember { mutableStateListOf<StoreModel>() }
    var showPopularLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        viewModel.loadPopular(id).observeForever {
            popular.clear()
            popular.addAll(it)
            showPopularLoading = false
        }
    }

    val nearest = remember { mutableStateListOf<StoreModel>() }
    var showNearestLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        viewModel.loadNearest(id).observeForever {
            nearest.clear()
            nearest.addAll(it)
            showNearestLoading = false
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.grey))
    ) {
        item { TopTitle(title, onBackClick) }
        item { Search() }
        item { SubCategorySection(subCategory, showSubCategoryLoading) }
        item { PopularSection(popular, showPopularLoading) }
        item { NearestSection(nearest, showNearestLoading) }
    }
}

