@Composable
fun CategoryItem(
    category:CategoryModel,
    modifier: Modifier=Modifier,
    onItemClick:()->Unit
){
    Column (
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = colorResource(R.color.white),
                shape = RoundedCornerShape(13.dp)
            )
            .clickable (onClick = onItemClick)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        
    }
}