package dev.maruffirdaus.w3_androidstudygroup.ui.explore

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.maruffirdaus.w3_androidstudygroup.ui.theme.StoryspaceTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar() {
    Column(modifier = Modifier.fillMaxWidth()) {
        var query by remember { mutableStateOf("") }
        SearchBar(
            query = query,
            onQueryChange = {
                query = it
            },
            onSearch = {},
            active = false,
            onActiveChange = {},
            modifier = Modifier.align(Alignment.CenterHorizontally),
            placeholder = {
                Text("Search Storyspace")
            },
            trailingIcon = {
                IconButton(onClick = {}) {
                    Icon(Icons.Outlined.Search, "Search")
                }
            }
        ) {}
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider()
    }
}

@Preview
@Composable
private fun SearchTopBarPreview() {
    StoryspaceTheme(dynamicColor = false) {
        Surface {
            SearchTopBar()
        }
    }
}