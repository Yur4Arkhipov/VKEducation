package com.example.vkeducation.presentation.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.vkeducation.R
import com.example.vkeducation.domain.model.AppItem
import com.example.vkeducation.presentation.theme.VKEducationTheme
import com.example.vkeducation.presentation.theme.card_background
import com.example.vkeducation.presentation.theme.on_accent
import com.example.vkeducation.presentation.theme.screen_background
import com.example.vkeducation.presentation.theme.text_primary
import com.example.vkeducation.presentation.theme.text_secondary
import com.example.vkeducation.presentation.theme.toolbar_accent

@Composable
internal fun AppsScreen(
    onAppClick: (AppItem) -> Unit,
) {
    val viewModel: AppsViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            Toolbar()
        },
        containerColor = screen_background
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(horizontal = 12.dp),
        ) {
            when (val currentState = state) {
                is AppsState.Loading -> {

                }

                is AppsState.Error -> {

                }

                is AppsState.Content -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        item {
                            Spacer(Modifier.padding(vertical = 10.dp))
                            Column {
                                Text(
                                    text = stringResource(R.string.catalog_title),
                                    style = TextStyle(
                                        fontSize = 26.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = text_primary
                                    )
                                )
                                Spacer(Modifier.height(5.dp))
                                Text(
                                    text = stringResource(R.string.catalog_subtitle),
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        color = text_secondary
                                    )
                                )
                            }
                        }
                        items(
                            items = currentState.apps,
                            key = { it.id }
                        ) { app ->
                            AppCard(
                                app = app,
                                onClick = { onAppClick(app) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun Toolbar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(toolbar_accent)
            .height(80.dp)
            .padding(horizontal = 12.dp)
            .windowInsetsPadding(WindowInsets.statusBars),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(card_background),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.vk_logo),
                style = TextStyle(
                    color = toolbar_accent,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black
                )
            )
        }
        Text(
            text = stringResource(R.string.toolbar_title),
            style = TextStyle(
                color = on_accent,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AppsScreenPreview() {
    VKEducationTheme(dynamicColor = false) {
        AppsScreen(
            onAppClick = {}
        )
    }
}
