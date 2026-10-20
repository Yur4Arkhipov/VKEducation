package com.example.vkeducation.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vkeducation.R
import com.example.vkeducation.data.apps
import com.example.vkeducation.domain.model.AppItem
import com.example.vkeducation.presentation.theme.VKEducationTheme
import com.example.vkeducation.presentation.theme.appItemColor
import com.example.vkeducation.presentation.theme.card_background
import com.example.vkeducation.presentation.theme.on_accent
import com.example.vkeducation.presentation.theme.screen_background
import com.example.vkeducation.presentation.theme.text_description
import com.example.vkeducation.presentation.theme.text_primary
import com.example.vkeducation.presentation.theme.text_secondary
import com.example.vkeducation.presentation.theme.toolbar_accent

@Composable
internal fun AppsScreen(onAppClick: (AppItem) -> Unit = {}) {
    Scaffold(
        containerColor = screen_background
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(horizontal = 16.dp)
        ) {
            Toolbar()
            Spacer(Modifier.padding(vertical = 10.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
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
                    items = apps,
                    key = { it.id }
                ) {
                    app -> AppCard(app, onClick = { onAppClick(app) })
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
            .height(60.dp),
        verticalAlignment = Alignment.CenterVertically
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

@Composable
internal fun AppCard(app: AppItem, onClick: () -> Unit = {}) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(18.dp),
        color = card_background,
        shadowElevation = 1.dp
    ) {
        Row(
            modifier = Modifier
                .padding(
                    horizontal = 10.dp,
                    vertical = 10.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onClick
            ) {
                Icon(
                    painter = painterResource(app.icon),
                    contentDescription = null
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 14.dp)
            ) {
                Text(
                    text = stringResource(app.name),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = text_primary
                    )
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = stringResource(app.description),
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                        color = text_description
                    )
                )
                Spacer(Modifier.height(9.dp))
                Text(
                    text = stringResource(app.category).uppercase(),
                    style = TextStyle(
                        fontSize = 10.sp,
                        lineHeight = 14.sp,
                        letterSpacing = 0.7.sp,
                        fontWeight = FontWeight.Bold,
                        color = appItemColor
                    ),
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(appItemColor.copy(alpha = 0.10f))
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun AppsScreenPreview() {
    VKEducationTheme(dynamicColor = false) { AppsScreen() }
}
