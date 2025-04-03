package com.glyadgzl.nearbarber.Results

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.glyadgzl.nearbarber.R

class ResultsActivity : AppCompatActivity() {
    private var id: String = ""
    private var title: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        id=intent.getStringExtra(name="id")?:""
        title=intent.getStringExtra(name="title")?:""

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

var showsubCategoryLoading by remember { mutableStateOf(value: true) }

LaunchedEffect (Unit) {
    viewModel.loadSubCategory(id).observeForever {
        subCategory.clear()
        subCategory.addAll(it)
        showsubCategoryLoading = false
    }
}


val popular = remember { mutableStateListOf<StoreModel>() }

var showPopularLoading by remember { mutableStateOf(value: true) }

LaunchedEffect (Unit) {
    viewModel.loadPopular(id).observeForever {
        popular.clear()
        popular.addAll(it)
        showPopularLoading = false
    }
}

    LazyColumn (
        modifier=Modifier.fillMaxSize().
        background(color = colorResource(R.color.light_blue))
    ){ 
        item{TopTitle(title,onBackClick)}
        item{
            Search()
        }
        item{SubCategorySection(subCategory,showsubCategoryLoading)}
        item{PopularSection(popular,showPopularLoading)}
    }
}