@Composable
fun NearestSection(list:SnapshotStateList<NearestModel>, showNearestLoading: Boolean) {
    Row(
        Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
     ) {
        Text(
            text = "Nearest Stores",
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "See all",
            color = Color.Black,
            fontSize = 16.sp,
            style = TextStyle(textDecoration = TextDecoration.Underline)
        )
     }
     if(showNearestLoading){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }

     }else{
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp)
         ) {
            items(list.size) { index ->
                ItemsNearest(item = list[index])
            }
         }
        }
    }
         
         @Composable
         fun ItemsNearest(item: StoreModel) {
            val context = LocalContext.current
            Row(
                modifier=Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(10.dp))
                .padding(8.dp)
                .clickable {
                    // Handle click event here
                }
            ){
                StoreImage(item=item)
                StoreDetails(item=item)
            }
}
@Composable
fun StoreImage(item: StoreModel) {
    AsyncImage(
        model = item.ImagePath,
        contentDescription = null,
        modifier = Modifier
            .size(95.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = colorResource(R.color.blue),shape = RoundedCornerShape(10.dp)),
        contentScale = ContentScale.Crop
    )
   
}