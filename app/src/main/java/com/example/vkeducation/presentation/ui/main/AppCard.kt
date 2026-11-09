package com.example.vkeducation.presentation.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.vkeducation.domain.model.AppItem
import com.example.vkeducation.presentation.theme.VKEducationTheme
import com.example.vkeducation.presentation.theme.appItemColor
import com.example.vkeducation.presentation.theme.card_background
import com.example.vkeducation.presentation.theme.text_description
import com.example.vkeducation.presentation.theme.text_primary

@Composable
internal fun AppCard(
    app: AppItem,
    onClick: () -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
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
                AsyncImage(
                    model = app.iconUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(card_background)
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 14.dp)
            ) {
                Text(
                    text = app.name,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = text_primary
                    )
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = app.description,
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                        color = text_description
                    )
                )
                Spacer(Modifier.height(9.dp))
                Text(
                    text = app.category.uppercase(),
                    style = TextStyle(
                        fontSize = 10.sp,
                        lineHeight = 14.sp,
                        letterSpacing = 0.7.sp,
                        fontWeight = FontWeight.Bold,
                        color = appItemColor
                    ),
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(appItemColor.copy(alpha = 0.10f))
                        .padding(
                            horizontal = 10.dp,
                            vertical = 6.dp
                        )
                )
            }
        }
    }
}

@Composable
@Preview
fun AppCardPreview() {
    VKEducationTheme {
        AppCard(
            AppItem(
                id = 1,
                name = "QuickNote",
                description = "Лёгкое приложение для заметок и напоминаний",
                category = "Производительность",
                iconUrl = "https://fastly.picsum.photos/id/237/200/200.jpg?hmac=zHUGikXUDyLCCmvyww1izLK3R3k8oRYBRiTizZEdyfI"
            ),
            onClick = { }
        )
    }
}
