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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.glyadgzl.nearbarber.R
@Composable
@Preview
fun TopTitle(title:String="title",onBackClick:()->Unit={}) {
   Box(
       modifier = Modifier
           .fillMaxWidth()
           .height(150.dp)
           .background(color = colorResource(R.color.grey))
   ) {
       Row(
           modifier = Modifier
               .align(Alignment.Center)
               .padding(horizontal = 16.dp),
           horizontalArrangement = Arrangement.Start,
           verticalAlignment = Alignment.CenterVertically
       ) {
           Image(painter = painterResource(R.drawable.back),
               contentDescription = null,
               modifier = Modifier
                   .clickable {onBackClick()}
           )
           
           Text(
            text = title,
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp),
            fontWeight = FontWeight.Bold
         )

       }
    }
}