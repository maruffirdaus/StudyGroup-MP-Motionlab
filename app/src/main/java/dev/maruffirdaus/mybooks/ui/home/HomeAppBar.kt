package dev.maruffirdaus.mybooks.ui.home

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.maruffirdaus.mybooks.R
import dev.maruffirdaus.mybooks.ui.theme.MyBooksTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar(
    onSearchButtonClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name)
            )
        },
        actions = {
            IconButton(
                onClick = onSearchButtonClick
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = stringResource(R.string.search_books)
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun HomeAppBarPreview() {
    MyBooksTheme {
        HomeAppBar(
            onSearchButtonClick = {}
        )
    }
}