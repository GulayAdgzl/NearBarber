import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.glyadgzl.nearbarber.R
import kotlinx.coroutines.NonDisposableHandle.parent

@Composable
@Preview
fun TopBar(){
    val horizontalPadding=16.dp
    val verticalPadding=16.dp


    ConstraintLayout(modifier= Modifier.fillMaxWidth().wrapContentHeight()){
        val(bluebox,profile,building)=createRefs()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(255.dp)
                .background(color = colorResource(R.color.grey))
                .constrainAs(bluebox) {
                    top.linkTo(parent.top)
                }
        )
        Image(
            painter = painterResource(R.drawable.g),
            contentDescription = null,
            modifier = Modifier.constrainAs(building) {
                bottom.linkTo(bluebox.bottom)
            }
        )

        Image(
            painter = painterResource(R.drawable.profile),
            contentDescription = null,
            modifier = Modifier

                .padding(horizontal = horizontalPadding, vertical = verticalPadding)
                .constrainAs(profile) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
        )

    }
}