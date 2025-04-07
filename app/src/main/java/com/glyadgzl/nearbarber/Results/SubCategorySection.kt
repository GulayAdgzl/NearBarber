import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.glyadgzl.nearbarber.R
@Composable
fun SubCategorySection(
    subCategory: List<CategoryModel>,
    showsubCategoryLoading: Boolean
) {
   Row (
        Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
    ) {
        Text(
            text = "Categories",
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(1f)
        )

        if (showsubCategoryLoading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp)
            ) {
                items(subCategory.size) { index ->
                    Category(
                        item = subCategory[index],
                        onItemClick = {}
                    )
                }
            }
        }
    }
}@Composable
fun Category(item: CategoryModel, onItemClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .wrapContentSize()
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .padding(8.dp)
            .clickable(onClick = onItemClick),
    ) {
        Box(
            modifier = Modifier
                .size(135.dp, 160.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(colorResource(id = R.color.grey), shape = RoundedCornerShape(10.dp))
        ) {
            AsyncImage(
                model = item.ImagePath,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )

            // Added gradient overlay for better text visibility
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f)),
                            startY = 0f,
                            endY = 50f
                        )
                    )
                    .padding(10.dp)
            ) {
                Text(
                    text = item.Name,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
// Create Preview for SubCategorySection
@Preview(showBackground = true)
@Composable
fun SubCategorySectionPreview() {
    // Sample data for preview
    val sampleCategories = listOf(
        CategoryModel(
            Id = 1,
            Name = "Restaurants",
            ImagePath = "https://example.com/restaurants.jpg"
        ),
        CategoryModel(
            Id = 2,
            Name = "Cafés",
            ImagePath = "https://example.com/cafes.jpg"
        ),
        CategoryModel(
            Id= 3,
            Name = "Nightlife",
            ImagePath = "https://example.com/nightlife.jpg"
        ),
        CategoryModel(
            Id = 4,
            Name = "Shopping",
            ImagePath = "https://example.com/shopping.jpg"
        )
    )

    SubCategorySection(
        subCategory = sampleCategories,
        showsubCategoryLoading = false
    )
}

// Create Preview for single Category item
@Preview(showBackground = true)
@Composable
fun CategoryItemPreview() {
    val sampleCategory = CategoryModel(
        Id = 1,
        Name = "Restaurants",
        ImagePath = "https://example.com/restaurants.jpg"
    )

    Category(
        item = sampleCategory,
        onItemClick = {}
    )
}

// Full screen preview with both sections
@Preview(showBackground = true)
@Composable
fun FullScreenPreview() {
    // Sample data
    val categories = listOf(
        CategoryModel(
            Id = 1,
            Name = "Restaurants",
            ImagePath = "https://example.com/restaurants.jpg"
        ),
        CategoryModel(
            Id = 2,
            Name = "Cafés",
            ImagePath = "https://example.com/cafes.jpg"
        ),
        CategoryModel(
            Id = 3,
            Name = "Nightlife",
            ImagePath = "https://example.com/nightlife.jpg"
        )
    )

    val stores = listOf(
        StoreModel(
            Id = 1,
            Title = "Italian Restaurant",
            ShortAddress = "2 km away",
            ImagePath = "https://example.com/italian.jpg"
        ),
        StoreModel(
            Id= 2,
            Title = "Coffee Shop",
            ShortAddress = "1.5 km away",
            ImagePath = "https://example.com/coffee.jpg"
        )
    )

    Surface (
        color = Color(0xFFF6F6F6),
        modifier = Modifier.fillMaxSize()
    ) {
        Column {


            SubCategorySection(
                subCategory = categories,
                showsubCategoryLoading = false
            )

            PopularSection(
                list = stores,
                showPopularLoading = false
            )
        }
    }
}