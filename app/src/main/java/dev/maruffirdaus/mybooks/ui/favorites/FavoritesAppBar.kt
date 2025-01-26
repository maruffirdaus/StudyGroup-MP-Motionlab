package dev.maruffirdaus.mybooks.ui.favorites

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.maruffirdaus.mybooks.R
import dev.maruffirdaus.mybooks.ui.theme.MyBooksTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesAppBar(
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.favorites)
            )
        },
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun FavoritesAppBarPreview() {
    MyBooksTheme {
        FavoritesAppBar()
    }
}