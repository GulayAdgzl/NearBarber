@Composable
fun NearestSection(list:SnapshotStateList<NearestModel>, showNearestLoading: Boolean) {
    Row(
        Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
     ) {
        Text(
            text = "Popular Stores",
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
            
            Column (
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .wrapContentSize()
                    .background(Color.White, shape = RoundedCornerShape(10.dp))
                    .padding(8.dp)
                    .clickable {

                    }
            ){
                AsyncImage(
   model = item.ImagePath,
   contentDescription = null,
   modifier = Modifier
       .size(135.dp, 90.dp)
       .clip(RoundedCornerShape(10.dp))
       .background(colorResource(R.color.grey), shape = RoundedCornerShape(10.dp)),
   contentScale = ContentScale.Crop
)
Text(
   text=item.Title,
   color = Color.Black,
   fontSize = 14.sp,
   fontWeight = FontWeight.Bold,
   maxLines = 1,
   overflow = TextOverflow.Ellipsis,
   modifier = Modifier.padding(top=8.dp)
)
Row(
    Modifier.padding(top=8.dp)
){
Image(
    painter = painterResource(R.drawable.location),
    contentDescription = null,
    
)

Text(
   text=item.ShortAddress,
   color = Color.Black,
   fontSize = 14.sp,
   fontWeight = FontWeight.SemiBold,
   maxLines = 1,
   overflow = TextOverflow.Ellipsis,
   modifier = Modifier.padding(start=8.dp)
)
}





            }
         }
