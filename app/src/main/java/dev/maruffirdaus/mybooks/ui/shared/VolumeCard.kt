package dev.maruffirdaus.mybooks.ui.shared

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import dev.maruffirdaus.mybooks.R
import dev.maruffirdaus.mybooks.data.model.Volume
import dev.maruffirdaus.mybooks.data.model.VolumeInfo
import dev.maruffirdaus.mybooks.ui.theme.MyBooksTheme

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun VolumeCard(
    volume: Volume,
    favoriteButtonType: Int,
    onFavoriteButtonClick: (Volume) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val volumeInfo = volume.volumeInfo

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
        Box {
            var isFavoriteButtonEnabled by remember { mutableStateOf(true) }

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
                    Spacer(
                        modifier = Modifier.height(60.dp)
                    )
                }
            }
            OutlinedButton(
                onClick = {
                    onFavoriteButtonClick(volume)
                    isFavoriteButtonEnabled = false
                },
                modifier = Modifier
                    .padding(12.dp)
                    .align(Alignment.BottomEnd),
                enabled = isFavoriteButtonEnabled
            ) {
                Icon(
                    painter = painterResource(
                        if (favoriteButtonType == FavoriteButtonType.ADD) {
                            R.drawable.ic_favorite
                        } else {
                            R.drawable.ic_delete
                        }
                    ),
                    contentDescription = stringResource(
                        if (favoriteButtonType == FavoriteButtonType.ADD) {
                            R.string.add_to_favorites
                        } else {
                            R.string.remove
                        }
                    )
                )
                Spacer(
                    modifier = Modifier.width(8.dp)
                )
                Text(
                    text = stringResource(
                        if (favoriteButtonType == FavoriteButtonType.ADD) {
                            R.string.add_to_favorites
                        } else {
                            R.string.remove
                        }
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
        }
    }
}

@Preview
@Composable
private fun VolumeCardPreview() {
    MyBooksTheme {
        VolumeCard(
            volume = Volume(
                volumeInfo = VolumeInfo(
                    title = "Example"
                ),
                id = ""
            ),
            favoriteButtonType = FavoriteButtonType.ADD,
            onFavoriteButtonClick = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}

object FavoriteButtonType {
    const val ADD = 0
    const val REMOVE = 1
}