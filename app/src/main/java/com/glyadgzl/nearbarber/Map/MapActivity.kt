package com.glyadgzl.nearbarber.Map

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.glyadgzl.nearbarber.R

class MapActivity : AppCompatActivity() {
    private lateinit var item: StoreModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        setContent {
            item = intent.getSerializableExtra("object") as StoreModel
            
            val latitude = item.latitude
            val longitude = item.longitude
            MapScreen(
            LatLng(latitude, longitude),
                 item
            )
        }
    }
}
@Composable
fun MapScreen(latLng: LatLng, item: StoreModel) {
   val cameraPositionState = rememberCameraPositionState {
       position = CameraPosition.fromLatLngZoom(latLng, zoom: 15f)
   }
   
   val context = LocalContext.current
   
   ConstraintLayout {
       val (map, detail) = createRefs()
       
       GoogleMap(
           modifier = Modifier
               .fillMaxSize()
               .constrainAs(map) {
                   centerTo(parent)
               },
           cameraPositionState = cameraPositionState
       ) {
           Marker(state = MarkerState(position = latLng), title = "Location Marker")
       }
       
       LazyColumn (
           modifier = Modifier
               .wrapContentHeight()
               .padding(horizontal = 24.dp, vertical = 32.dp)
               .fillMaxWidth()
               .background(Color.White, shape =RoundedCornerShape(10.dp)) 
               .padding(16.dp)
               .constrainAs(detail) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
             }
       ) {
        item{ItemsNearest( item)}
        item{}
        }
   }
       }