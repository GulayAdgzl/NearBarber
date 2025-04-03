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
                   .clickable { }
           )
           
           Text(text =title)
       }
    }
}