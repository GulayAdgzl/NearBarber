import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import coil.compose.AsyncImage
import com.glyadgzl.nearbarber.R
import com.glyadgzl.nearbarber.Results.ResultsActivity



@Composable
fun CategorySection(
    categories: MutableList<CategoryModel>,
    showCategoryLoading: Boolean
) {


    if (showCategoryLoading) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
           CircularProgressIndicator()
        }
    } else {
        val rows = categories.chunked(3)
        val context = LocalContext.current

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            rows.forEach { row ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    row.forEachIndexed { index, categoryModel ->
                        CategoryItem(
                            category = categoryModel,
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 8.dp),
                            onItemClick = {
                                // Handle click event
                                val intent= Intent(context, ResultsActivity::class.java).apply{
                                    putExtra("id",categoryModel.Id)
                                    putExtra("title",categoryModel.Name)
                                }
                                startActivity(context,intent,null)
                            }
                        )
                    }

                    if (row.size < 3) {
                        repeat(3 - row.size) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryItem(
    category: CategoryModel,
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit
) {
    Box(
        modifier = modifier
            .aspectRatio(0.75f) // Kart oranı, dikey daha uzun görünüm
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onItemClick)
    ) {
        AsyncImage(
            model = category.ImagePath,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )

        // Alt yazı overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.6f)),
                        startY = 200f
                    )
                )
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(12.dp)
        ) {
            Text(
                text = "${category.Name}",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategorySectionPreview() {
    val sampleCategories = remember {
        mutableStateListOf(
            CategoryModel(1, "Technology", R.drawable.sample.toString()),
            CategoryModel(2, "Science", "https://via.placeholder.com/150"),
            CategoryModel(3, "Art", "https://via.placeholder.com/150"),
            CategoryModel(4, "Sports", "https://via.placeholder.com/150"),
            CategoryModel(5, "Music", "https://via.placeholder.com/150")
        )
    }

    CategorySection(
        categories = sampleCategories,
        showCategoryLoading = false
    )
}
