import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.glyadgzl.nearbarber.R

@Composable
@Preview
fun MyBottomBar(){
    val bottomMenuItemsList = prepareBottomMenu()
    val context = LocalContext.current
    var selected by remember { mutableStateOf(value = "Home") }

    BottomAppBar(

        /*background = colorResource(R.color.white),
        elevation = 3.dp*/
        containerColor = BottomAppBarDefaults.containerColor,

    ) {
        bottomMenuItemsList.forEach { bottomMenuItems ->
            BottomNavigationItem(
                selected = (selected == bottomMenuItems.label),
                onClick = {
                    selected = bottomMenuItems.label
                    Toast.makeText(context, bottomMenuItems.label, Toast.LENGTH_SHORT).show()
                },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = bottomMenuItems.icon,
                            contentDescription = null,
                            tint = colorResource(R.color.darkBrown),
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .size(20.dp)
                        )
                        Text(
                            text=bottomMenuItems.label,
                            fontSize = 12.sp,
                            color= colorResource(R.color.darkBrown),
                            modifier = Modifier.padding(top = 8.dp),
                        )
                    }
                }
            )
        }
    }

}
data class BottomMenuItem(
    val label:String,val icon: Painter
)

@Composable
fun prepareBottomMenu():List<BottomMenuItem>{
    val bottomMenuItemsList= listOf(
        BottomMenuItem("Home", painterResource(id = R.drawable.home)),

        BottomMenuItem("Profile", painterResource(id = R.drawable.pro)),
        BottomMenuItem("ChatBot", painterResource(id = R.drawable.chat))
    )
    return bottomMenuItemsList
}