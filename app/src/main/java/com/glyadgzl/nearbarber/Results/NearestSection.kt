import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun NearestSection(list: SnapshotStateList<StoreModel>, showNearestLoading: Boolean) {
    Row(
        Modifier
            .padding(horizontal = 16. dp)
            .padding(top = 16.dp)
     ) {
        Text(
            text = "Nearest Stores",
            color = Color.Black,
            fontSize = 20. sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "See all",
            color = Color.Black,
            fontSize = 16.sp,
            style = TextStyle(textDecoration = TextDecoration.Underline)
        )
     }
     if(showNearestLoading){
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }

     }else{
        LazyColumn(
            modifier = Modifier
            .height(400.dp)
                .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp)
         ) {
            items(list.size) { index ->
                ItemsNearest(item = list[index])
            }
         }
        }
    }
         
         @Composable
         fun ItemsNearest(item: StoreModel) {
            val context = LocalContext.current
            Row(
                modifier=Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(10.dp))
                .padding(8.dp)
                .clickable {
                    // Handle click event here

                    val intent=Intent(context, MapActivity::class.java).apply{
                        putExtra("object", item)
                    }
                    startActivity(context, intent, null)
                }
            ){
                StoreImage(item=item)
                StoreDetail(item=item)
            }
}

@Composable
fun StoreImage(item: StoreModel) {
    AsyncImage(
        model = item.ImagePath,
        contentDescription = null,
        modifier = Modifier
            .size(95.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = colorResource(R.color.blue),shape = RoundedCornerShape(10.dp)),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun StoreDetail(item: StoreModel) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(start = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = item.Title,
            color = Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(R.drawable.location), contentDescription = null)
            Text(
                text = item.Address,
                color = Color.Black,
                fontSize = 12.sp,
                maxLines = 1,
                modifier = Modifier.padding(start = 4.dp)
            )

            Text(
                text = item.Activity,
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1
            )
            Text(
                text = "Hours:${item.Hours}",
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1
            )

        }
    }
}
