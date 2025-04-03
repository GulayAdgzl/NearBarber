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
    ) { 
    }
}