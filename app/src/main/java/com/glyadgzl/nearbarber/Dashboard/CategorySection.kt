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
        AsyncImage(
    model = category.ImagePath,
    contentDescription = null,
    modifier=Modifier.size(65.dp)
)
Text(
    text=category.Name,
    color = Color.Black,
    fontSize = 14.sp,
    fontWeight = FontWeight.SemiBold,
    modifier = Modifier.padding(top=8.dp)
)
    }
}