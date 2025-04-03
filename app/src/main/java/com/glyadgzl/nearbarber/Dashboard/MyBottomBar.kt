@Composable
@Preview
fun MyBottomBar(){
    val bottomMenuItemsList=
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