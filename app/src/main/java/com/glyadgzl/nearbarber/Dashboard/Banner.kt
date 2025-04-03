@Composable
fun Banner(banners: SnapshotStateList<BannerModel>, showBannerLoading: Boolean) {
    if(showBannerLoading){
        Box(modifier = Modifier
            .fillMaxSize()
            .height(200.dp),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
    }else{
        Sliding(banners = banners)
        
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Sliding(
    pagerState:PagerState= remember { PagerState() },
    banners: List<BannerModel>
){
    HorizontalPager(count=banners.size,
        state = pagerState
    ) { page ->
        AsyncImage(
   model = ImageRequest.Builder(LocalContext.current)
       .data(banners[page].image)
       .build(),
   contentDescription = null,
   contentScale = ContentScale.FillBounds,
   modifier = Modifier
       .fillMaxSize()
       .padding(16.dp)
       .clip(RoundedCornerShape(10.dp))
       .height(150.dp)
)
    }
}