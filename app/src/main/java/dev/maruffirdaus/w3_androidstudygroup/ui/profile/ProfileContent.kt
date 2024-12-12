package dev.maruffirdaus.w3_androidstudygroup.ui.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import dev.maruffirdaus.w3_androidstudygroup.R
import dev.maruffirdaus.w3_androidstudygroup.data.Data
import dev.maruffirdaus.w3_androidstudygroup.ui.home.StoryCard
import dev.maruffirdaus.w3_androidstudygroup.ui.theme.StoryspaceTheme

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ProfileContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        var selectedTabIndex by remember { mutableIntStateOf(0) }
        val titles = listOf("Stories", "Media", "Likes")
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            GlideImage(
                model = R.drawable.maruffirdaus,
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(96.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Ma'ruf Firdaus",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    "maruffirdaus",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        PrimaryTabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.fillMaxWidth()
        ) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Text(title)
                    }
                )
            }
        }
        when (selectedTabIndex) {
            0 -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    itemsIndexed(Data.maruffirdausStories) { index, story ->
                        StoryCard(
                            username = story.username,
                            profilePicture = story.profilePicture,
                            content = story.content
                        )
                        if (Data.maruffirdausStories.size - 1 != index) {
                            HorizontalDivider()
                        }
                    }
                }
            }

            1 -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Empty",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            2 -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Empty",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ProfileContentPreview() {
    StoryspaceTheme(dynamicColor = false) {
        Surface {
            ProfileContent()
        }
    }
}