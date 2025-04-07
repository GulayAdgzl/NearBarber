import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.glyadgzl.nearbarber.R
@Composable
fun MyBottomBar(navController: NavController) {
    val bottomMenuItemsList = prepareBottomMenu()
    val context = LocalContext.current
    var selected by remember { mutableStateOf("Home") }

    BottomAppBar {
        bottomMenuItemsList.forEach { bottomMenuItem ->
            BottomNavigationItem(
                selected = (selected == bottomMenuItem.label),
                onClick = {
                    selected = bottomMenuItem.label
                    Toast.makeText(context, bottomMenuItem.label, Toast.LENGTH_SHORT).show()

                    when (bottomMenuItem.label) {
                        "ChatBot" -> navController.navigate("chat")
                        "Home" -> navController.navigate("dashboard")
                        // İstersen burada Profile vs. de ekleyebilirsin
                    }
                },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = bottomMenuItem.icon,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .size(20.dp)
                        )
                    }
                }
            )
        }
    }
}

data class BottomMenuItem(
    val label: String,
    val icon: Painter
)

@Composable
fun prepareBottomMenu(): List<BottomMenuItem> {
    return listOf(
        BottomMenuItem("Home", painterResource(id = R.drawable.btn_1)),

        BottomMenuItem("ChatBot", painterResource(id = R.drawable.btn_2))
    )
}
