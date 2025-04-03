@Composable
fun SubCategorySection(
    subCategory: MutableList<CategoryModel>,
    showsubCategoryLoading: Boolean
) {
    if(showsubCategoryLoading) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpacedBy(12.dp),
                contentPadding = PaddingValues
                (start=16.dp,end=16.dp,top=8.dp)

            
        ){
            items(subCategory.size) { index ->
                Category(item=subCategory[index],
                    onItemClick={}
                )
            }
            
            @Composable
            fun Category(item: CategoryModel, onItemClick: () -> Unit) {
                Column (modifier = Modifier
                    .size(85.dp,95.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                    .clickable (onClick = onItemClick),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    AsyncImage(model = item.ImagePath
                        ,contentDescription = null
                    )
                }
        }
     
    }
   
}