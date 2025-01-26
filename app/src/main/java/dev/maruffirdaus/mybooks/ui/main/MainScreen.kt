package dev.maruffirdaus.mybooks.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.maruffirdaus.mybooks.R
import dev.maruffirdaus.mybooks.ui.favorites.FavoritesAppBar
import dev.maruffirdaus.mybooks.ui.favorites.FavoritesScreen
import dev.maruffirdaus.mybooks.ui.home.HomeAppBar
import dev.maruffirdaus.mybooks.ui.home.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MainScreen() {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    var selectedItem by rememberSaveable { mutableIntStateOf(0) }
    val items = listOf(stringResource(R.string.home), stringResource(R.string.favorites))
    val selectedIcons = listOf(
        painterResource(R.drawable.ic_home_filled),
        painterResource(R.drawable.ic_favorite_filled)
    )
    val unselectedIcons = listOf(
        painterResource(R.drawable.ic_home),
        painterResource(R.drawable.ic_favorite)
    )

    var showSearchDialog: (() -> Unit)? = null

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            when (selectedItem) {
                0 -> {
                    HomeAppBar(
                        onSearchButtonClick = {
                            showSearchDialog?.invoke()
                        },
                        scrollBehavior = scrollBehavior
                    )
                }

                1 -> {
                    FavoritesAppBar(
                        scrollBehavior = scrollBehavior
                    )
                }
            }
        },
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = index == selectedItem,
                        onClick = {
                            selectedItem = index
                        },
                        icon = {
                            Icon(
                                painter = if (index == selectedItem) {
                                    selectedIcons[index]
                                } else {
                                    unselectedIcons[index]
                                },
                                contentDescription = item
                            )
                        },
                        label = {
                            Text(
                                text = item
                            )
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        when (selectedItem) {
            0 -> {
                HomeScreen(
                    modifier = Modifier.padding(innerPadding),
                    provideShowSearchDialog = { providedShowSearchDialog ->
                        showSearchDialog = providedShowSearchDialog
                    }
                )
            }

            1 -> {
                FavoritesScreen(
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}