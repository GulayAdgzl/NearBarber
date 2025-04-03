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
        LazyColumn(
            modifier = Modifier
            .height(400.dp)
                .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
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
fun StoreDetail(item: StoreModel) {
   Column(
       modifier = Modifier
           .fillMaxHeight()
           .padding(start = 8.dp),
       verticalArrangement = Arrangement.spacedBy(8.dp)
   ) {
       Text(
           text = item.Title,
           color = Color.Black,
           fontSize = 14.sp,
           fontWeight = FontWeight.Bold,
           maxLines = 1
       )
       Row(verticalAlignment = Alignment.CenterVertically) {
           Image(painter = painterResource(R.drawable.location),contentDescription = null)
           Text(
   text = item.Address,
   color = Color.Black,
   fontSize = 12.sp,
   maxLines = 1,
   modifier = Modifier.padding(start = 4.dp)
)

Text(
   text = item.Activity,
   color = Color.Black,
   fontSize = 14.sp,
   fontWeight = FontWeight.SemiBold,
   maxLines = 1)
   Text(
   text = "Hours:${item.Hours}",
   color = Color.Black,
   fontSize = 14.sp,
   fontWeight = FontWeight.SemiBold,
   maxLines = 1)
       
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