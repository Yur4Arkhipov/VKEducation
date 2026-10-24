package com.example.vkeducation.presentation.ui.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.vkeducation.R
import com.example.vkeducation.data.apps
import com.example.vkeducation.presentation.ui.AppCard

@Composable
internal fun Storefront(
    onAppClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val pageApps = (apps + apps.first()).chunked(3)
    val pagerState = rememberPagerState(pageCount = { 3 })

    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.app_details_storefront),
            style = MaterialTheme.typography.headlineSmall,
        )
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
        ) { page ->
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                pageApps[page].forEach { app ->
                    AppCard(app = app, onClick = onAppClick)
                }
            }
        }
    }
}
