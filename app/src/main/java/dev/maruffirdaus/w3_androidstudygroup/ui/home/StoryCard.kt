package dev.maruffirdaus.w3_androidstudygroup.ui.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import dev.maruffirdaus.w3_androidstudygroup.R
import dev.maruffirdaus.w3_androidstudygroup.ui.theme.StoryspaceTheme

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun StoryCard(
    username: String,
    @DrawableRes profilePicture: Int?,
    content: String
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            if (profilePicture != null) {
                GlideImage(
                    model = profilePicture,
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    painter = rememberVectorPainter(Icons.Default.AccountCircle),
                    contentDescription = "Profile picture",
                    modifier = Modifier.size(48.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    username,
                    color = MaterialTheme.colorScheme.onSurface,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    content,
                    color = MaterialTheme.colorScheme.onSurface,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 5,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    IconButton(onClick = {}) {
                        Icon(Icons.Outlined.ThumbUp, "Like")
                    }
                    IconButton(onClick = {}) {
                        Icon(painterResource(R.drawable.ic_comment), "Comment")
                    }
                    IconButton(onClick = {}) {
                        Icon(painterResource(R.drawable.ic_share), "Share")
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Preview
@Composable
private fun StoryCardPreview() {
    StoryspaceTheme(dynamicColor = false) {
        Surface {
            StoryCard(
                username = "maruffirdaus",
                profilePicture = null,
                content = "Lorem ipsum odor amet, consectetuer adipiscing elit. Amet malesuada eu aliquam odio parturient viverra. Interdum eget tempor consectetur lacinia conubia. Venenatis laoreet malesuada efficitur convallis imperdiet iaculis rutrum. Pellentesque class ultricies tempor feugiat hendrerit tincidunt. Posuere rutrum varius velit pretium vestibulum iaculis. Magnis diam vivamus torquent iaculis fringilla sollicitudin ante. Risus habitasse libero etiam aliquam nostra cras ullamcorper."
            )
        }
    }
}