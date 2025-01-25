package dev.maruffirdaus.mybooks.ui.home

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import dev.maruffirdaus.mybooks.data.model.VolumeInfo
import dev.maruffirdaus.mybooks.ui.theme.MyBooksTheme

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun VolumeCard(
    volumeInfo: VolumeInfo,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    fun openLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

        context.startActivity(intent)
    }

    Card(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                volumeInfo.infoLink?.let { url ->
                    openLink(url)
                }
            }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            GlideImage(
                model = volumeInfo.imageLinks?.smallThumbnail?.let { imageUrl ->
                    if (imageUrl.startsWith("http")) {
                        "https" + imageUrl.substring(4)
                    } else {
                        imageUrl
                    }
                },
                contentDescription = null,
                modifier = Modifier.width(128.dp),
                alignment = Alignment.CenterStart,
                contentScale = ContentScale.FillWidth
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
            ) {
                volumeInfo.title?.let { title ->
                    Text(
                        text = title,
                        color = MaterialTheme.colorScheme.onSurface,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                volumeInfo.authors?.let { authors ->
                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )
                    Text(
                        text = authors.joinToString(),
                        color = MaterialTheme.colorScheme.onSurface,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                volumeInfo.description?.let { description ->
                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )
                    Text(
                        text = description,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun VolumeCardPreview() {
    MyBooksTheme {
        VolumeCard(
            volumeInfo = VolumeInfo(),
            modifier = Modifier.fillMaxWidth()
        )
    }
}