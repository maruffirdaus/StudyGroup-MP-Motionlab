package dev.maruffirdaus.w3_androidstudygroup.ui.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import dev.maruffirdaus.w3_androidstudygroup.R
import dev.maruffirdaus.w3_androidstudygroup.ui.theme.StoryspaceTheme
import dev.maruffirdaus.w3_androidstudygroup.ui.theme.sofiaFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar(scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()) {
    LargeTopAppBar(
        title = {
            Text(
                "Storyspace",
                fontFamily = sofiaFamily
            )
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(Icons.Outlined.Notifications, "Notifications")
            }
            IconButton(onClick = {}) {
                Icon(painterResource(R.drawable.ic_mail), "Messages")
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HomeAppBarPreview() {
    StoryspaceTheme(dynamicColor = false) {
        HomeAppBar()
    }
}