package dev.maruffirdaus.w3_androidstudygroup.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import dev.maruffirdaus.w3_androidstudygroup.R
import dev.maruffirdaus.w3_androidstudygroup.ui.explore.SearchContent
import dev.maruffirdaus.w3_androidstudygroup.ui.explore.SearchTopBar
import dev.maruffirdaus.w3_androidstudygroup.ui.home.HomeAppBar
import dev.maruffirdaus.w3_androidstudygroup.ui.home.HomeContent
import dev.maruffirdaus.w3_androidstudygroup.ui.profile.ProfileAppBar
import dev.maruffirdaus.w3_androidstudygroup.ui.profile.ProfileContent
import dev.maruffirdaus.w3_androidstudygroup.ui.theme.StoryspaceTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun MainScreen() {
    var selectedItem by remember { mutableIntStateOf(0) }
    val homeAppBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(
        modifier = if (selectedItem == 0) {
            Modifier.nestedScroll(homeAppBarScrollBehavior.nestedScrollConnection)
        } else {
            Modifier
        },
        topBar = {
            when (selectedItem) {
                0 -> {
                    HomeAppBar(scrollBehavior = homeAppBarScrollBehavior)
                }

                1 -> {
                    SearchTopBar()
                }

                2 -> {
                    ProfileAppBar()
                }
            }
        },
        bottomBar = {
            val items = listOf("Home", "Search", "Profile")
            val selectedIcons = listOf<@Composable () -> Unit>(
                { Icon(Icons.Filled.Home, "Home") },
                { Icon(Icons.Filled.Search, "Search") },
                {
                    GlideImage(
                        model = R.drawable.maruffirdaus,
                        contentDescription = "Profile",
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }
            )
            val unselectedIcons = listOf<@Composable () -> Unit>(
                { Icon(Icons.Outlined.Home, "Home") },
                { Icon(Icons.Outlined.Search, "Search") },
                {
                    GlideImage(
                        model = R.drawable.maruffirdaus,
                        contentDescription = "Profile",
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }
            )
            NavigationBar {
                items.forEachIndexed { index, _ ->
                    NavigationBarItem(
                        selected = selectedItem == index,
                        onClick = { selectedItem = index },
                        icon = if (selectedItem == index) selectedIcons[index] else unselectedIcons[index]
                    )
                }
            }
        },
        floatingActionButton = {
            when (selectedItem) {
                0 -> FloatingActionButton(onClick = {}) {
                    Icon(Icons.Outlined.Add, "Add")
                }
            }
        }
    ) { innerPadding ->
        when (selectedItem) {
            0 -> {
                HomeContent(modifier = Modifier.padding(innerPadding))
            }

            1 -> {
                SearchContent(modifier = Modifier.padding(innerPadding))
            }

            2 -> {
                ProfileContent(modifier = Modifier.padding(innerPadding))
            }
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    StoryspaceTheme(dynamicColor = false) {
        MainScreen()
    }
}