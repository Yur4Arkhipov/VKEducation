package com.example.vkeducation.presentation.ui.details.components

//@Composable
//internal fun Storefront(
//    onAppClick: () -> Unit,
//    modifier: Modifier = Modifier,
//) {
//    val pageApps = (apps + apps.first()).chunked(3)
//    val pagerState = rememberPagerState(pageCount = { 3 })
//
//    Column(modifier = modifier) {
//        Text(
//            text = stringResource(R.string.app_details_storefront),
//            style = MaterialTheme.typography.headlineSmall,
//        )
//        HorizontalPager(
//            state = pagerState,
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(400.dp),
//        ) { page ->
//            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
//                pageApps[page].forEach { app ->
////                    AppCard(app = app, onClick = onAppClick)
//                }
//            }
//        }
//    }
//}
