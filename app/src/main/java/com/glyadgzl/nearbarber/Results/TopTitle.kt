@Composable
@Preview
fun TopTitle(title:String="title",onBackClick:()->Unit={}) {
   Box(
       modifier = Modifier
           .fillMaxWidth()
           .height(150.dp)
           .background(color = colorResource(R.color.blue))
   ) {
       Row(
           modifier = Modifier
               .align(Alignment.Center)
               .padding(horizontal = 16.dp),
           horizontalArrangement = Arrangement.Start,
           verticalAlignment = Alignment.CenterVertically
       ) {
           Image(painter = painterResource(R.drawable.back),
               contentDescription = null,
               modifier = Modifier
                   .clickable {obBackClick()} 
           )
           
           Text(
            text = title + " Stores Result",
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp),
            fontWeight = FontWeight.Bold
         )
         Image(painter = painterResource(R.drawable.sample)
            , contentDescription = null)
       }
    }
}