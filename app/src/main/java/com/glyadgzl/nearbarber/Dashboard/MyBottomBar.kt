@Composable
@Preview
fun MyBottomBar(){
    val bottomMenuItemsList = prepareBottomMenu()
    val context = LocalContext.current
    var selected by remember { mutableStateOf(value = "Home") }
    
    BottomAppBar(
        backgroundColor = colorResource(R.color.white),
        elevation = 3.dp
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
    val label:String,val icon:Painter
)

@Composable
fun prepareBottomMenu():List<BottomMenuItem>{
    val bottomMenuItemsList= listOf(
        BottomMenuItem("Home", painterResource(id = R.drawable.home)),
      
        BottomMenuItem("Profile", painterResource(id = R.drawable.profile)),
        BottomMenuItem("ChatBot", painterResource(id = R.drawable.ichat))
    )
    return bottomMenuItemsList
}