@Composable
@Preview
fun TopBar(){
    val horizontalPadding=16.dp
    val verticalPadding=16.dp


    ConstraintLayout(modifier=Modifier.fillMaxWidth().wrapContentHeight()){
        val(bluebox,title1,title2,profile,building,whiteBox)=createRefs()
        Box(
    modifier = Modifier
        .fillMaxWidth()
        .height(255.dp)
        .background(color = colorResource(R.color.gray))
        .constrainAs(bluebox) {
            top.linkTo(parent.top)
        }
)
Image(
   painter = painterResource(R.drawable.building),
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