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

    }
}