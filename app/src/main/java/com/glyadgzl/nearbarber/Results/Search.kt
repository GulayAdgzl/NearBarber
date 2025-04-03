@Composable
@Preview
fun Search(){
   var text by rememberSaveable { mutableStateOf( value:"" ) }
   TextField(
       value=text,
       onValueChange={text=it},
       label={
           Text(text="Find stories, restaurants, product...",
               fontSize = 16.sp,
               color = Color.Black
           )
       }, shape = RoundedCornerShape(10.dp),
       leadingIcon = {
           Image(painter = painterResource(R.drawable.search_icon),
               contentDescription = null,
               modifier = Modifier.size(22.dp)
           )
       },
       colors = TextFieldDefaults.outlineTextFieldColors(
        backgroundColor =colorResource(R.color.white),
           focusedBorderColor = Color.Transparent,
           unfocusedLabelColor = Color.Transparent,
           textColor = Color.DarkGray,
           unfocusedBorderColor = Color.Transparent,
       ),
       modifier=Modifier
           .fillMaxWidth()
           .padding(16.dp)
           .height(50.dp)
           .background(colorResource(R.color.grey),CircleShape)
           
   )
    }