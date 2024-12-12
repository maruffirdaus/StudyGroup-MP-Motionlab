package dev.maruffirdaus.w3_androidstudygroup.ui.profile

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import dev.maruffirdaus.w3_androidstudygroup.R
import dev.maruffirdaus.w3_androidstudygroup.ui.theme.StoryspaceTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileAppBar() {
    TopAppBar(
        title = {},
        actions = {
            IconButton(onClick = {}) {
                Icon(painterResource(R.drawable.ic_logout), "Logout")
            }
        }
    )
}

@Preview
@Composable
fun ProfileAppBarPreview() {
    StoryspaceTheme(dynamicColor = false) {
        ProfileAppBar()
    }
}