package dev.maruffirdaus.w3_androidstudygroup.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.maruffirdaus.w3_androidstudygroup.data.Data
import dev.maruffirdaus.w3_androidstudygroup.ui.theme.StoryspaceTheme

@Composable
fun HomeContent(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        itemsIndexed(Data.stories) { index, story ->
            StoryCard(
                username = story.username,
                profilePicture = story.profilePicture,
                content = story.content
            )
            if (Data.stories.size - 1 != index) {
                HorizontalDivider()
            }
        }
    }
}

@Preview
@Composable
private fun HomeContentPreview() {
    StoryspaceTheme(dynamicColor = false) {
        Surface {
            HomeContent()
        }
    }
}