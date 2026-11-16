package com.example.vkeducation.presentation.ui.details.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vkeducation.R
import com.example.vkeducation.presentation.theme.VKEducationTheme
import com.example.vkeducation.presentation.ui.details.InstallState

@Composable
fun InstallButton(
    installState: InstallState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val text = when (installState) {
        is InstallState.NotInstalled -> R.string.install
        is InstallState.Installing -> R.string.cancel
        is InstallState.Installed -> R.string.installed
    }

    val enabled = installState !is InstallState.Installed

    Button(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(vertical = 12.dp),
        modifier = modifier,
        enabled = enabled,
    ) {
        Text(text = stringResource(text))
    }
}

@Preview
@Composable
private fun PreviewNotInstalled() {
    VKEducationTheme {
        InstallButton(
            installState = InstallState.NotInstalled,
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview
@Composable
private fun PreviewInstalling() {
    VKEducationTheme {
        InstallButton(
            installState = InstallState.Installing(45),
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview
@Composable
private fun PreviewInstalled() {
    VKEducationTheme {
        InstallButton(
            installState = InstallState.Installed,
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
