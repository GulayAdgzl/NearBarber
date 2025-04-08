import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.core.content.ContextCompat.startActivity
import coil.compose.AsyncImage
import com.glyadgzl.nearbarber.Map.MapActivity
import com.glyadgzl.nearbarber.R

@Composable
fun PopularSection(list: List<StoreModel>, showPopularLoading: Boolean) {
    Row(
        Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
     ) {
        Text(
            text = "Popüler Kuförler",
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(1f)
        )


     if(showPopularLoading){
         Box(
             modifier = Modifier
                 .fillMaxWidth()
                 .height(100.dp),
             contentAlignment = Alignment.Center
         ) {
             CircularProgressIndicator()


        }

     }else{
         LazyRow(
             modifier = Modifier
                 .fillMaxWidth(),
             horizontalArrangement = Arrangement.spacedBy(12.dp),
             contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp)
         ) {
            items(list.size) { index ->
                ItemsPopular(item = list[index])
            }
         }
        }
    }
    }
         
         @Composable
         fun ItemsPopular(item: StoreModel) {
            val context = LocalContext.current
            
            Column (
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .wrapContentSize()
                    .background(Color.White, shape = RoundedCornerShape(10.dp))
                    .padding(8.dp)
                    .clickable {
                        val intent= Intent(context, MapActivity::class.java).apply{
                            putExtra("object", item)
                        }
                        startActivity(context, intent, null)
                    }
            ){
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
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
Text(
   text=item.Title,
    color = Color.White,
    fontSize = 14.sp,
    fontWeight = FontWeight.Bold,
    maxLines = 1,
    overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                Row(
                    Modifier.padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
Image(
    painter = painterResource(R.drawable.location),
    contentDescription = null,
    
)

Text(
   text=item.ShortAddress,
    color = Color.Black,
    fontSize = 14.sp,
    fontWeight = FontWeight.Normal,
    maxLines = 1,
    overflow = TextOverflow.Ellipsis,
    modifier = Modifier.padding(start = 4.dp)
)
}





            }
         }

@Preview(showBackground = true)
@Composable
fun PreviewPopularSection() {
    PopularSection(list =listOf(
        StoreModel(
            Title = "Barber Pro",
            ImagePath = "", // ya da örnek bir URL
            ShortAddress = "Main Street, NY"
        ),
        StoreModel(
            Title = "Elite Cuts",
            ImagePath = "",
            ShortAddress = "5th Avenue, NY"
        )), showPopularLoading = false)
}
