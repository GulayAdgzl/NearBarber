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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.glyadgzl.nearbarber.R

@Composable
@Preview
fun Search(){
   var text by rememberSaveable  { mutableStateOf( "" ) }
   TextField(
       value=text,
       onValueChange={text=it},
       label={
           Text(text="Kuaför Ara",
               fontSize = 16.sp,
               color = Color.Black
           )
       }, shape = RoundedCornerShape(10.dp),
       leadingIcon = {
           Image(painter = painterResource(R.drawable.search_icon),
               contentDescription = null,
               modifier = Modifier.size(22.dp)
           )
       },
       colors = TextFieldDefaults.outlinedTextFieldColors(
        backgroundColor =colorResource(R.color.white),
           focusedBorderColor = Color.Transparent,
           unfocusedLabelColor = Color.Transparent,
           textColor = Color.DarkGray,
           unfocusedBorderColor = Color.Transparent,
       ),
       modifier=Modifier
           .fillMaxWidth()
           .padding(16.dp)
           .height(50.dp)
           .background(colorResource(R.color.grey),CircleShape)
           
   )
    }