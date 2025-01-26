package dev.maruffirdaus.mybooks.ui.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.maruffirdaus.mybooks.R
import dev.maruffirdaus.mybooks.data.local.MyBooksDatabase
import dev.maruffirdaus.mybooks.data.model.Volume
import dev.maruffirdaus.mybooks.data.model.VolumeInfo
import dev.maruffirdaus.mybooks.ui.shared.FavoriteButtonType
import dev.maruffirdaus.mybooks.ui.shared.VolumeCard
import dev.maruffirdaus.mybooks.ui.theme.MyBooksTheme
import kotlinx.coroutines.launch

@Composable
fun FavoritesScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var isLoading by remember { mutableStateOf(true) }
    var volumes by remember { mutableStateOf(emptyList<Volume>()) }

    fun removeFromFavorites(volume: Volume) {
        scope.launch {
            MyBooksDatabase.getDatabase(context).volumeDao().delete(volume)
            volumes = MyBooksDatabase.getDatabase(context).volumeDao().getVolumes()
        }
    }

    LaunchedEffect(Unit) {
        volumes = MyBooksDatabase.getDatabase(context).volumeDao().getVolumes()
        isLoading = false
    }

    FavoritesScreenContent(
        volumes = volumes,
        onRemoveButtonClick = { volume ->
            removeFromFavorites(volume)
        },
        modifier = modifier,
        loading = isLoading,
    )
}

@Composable
fun FavoritesScreenContent(
    volumes: List<Volume>,
    onRemoveButtonClick: (Volume) -> Unit,
    modifier: Modifier = Modifier,
    loading: Boolean = false,
) {
    when {
        loading -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        volumes.isEmpty() -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.favorite_books_empty_message),
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }

        else -> {
            LazyColumn(
                modifier = modifier.fillMaxSize()
            ) {
                item {
                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )
                }
                items(
                    items = volumes,
                    key = { volume ->
                        volume.id
                    }
                ) { volume ->
                    VolumeCard(
                        volume = volume,
                        favoriteButtonType = FavoriteButtonType.REMOVE,
                        onFavoriteButtonClick = { volumeFromCard ->
                            onRemoveButtonClick(volumeFromCard)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
                item {
                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun FavoritesScreenPreview() {
    MyBooksTheme {
        Scaffold(
            topBar = {
                FavoritesAppBar()
            }
        ) { innerPadding ->
            FavoritesScreenContent(
                volumes = listOf(
                    Volume(
                        volumeInfo = VolumeInfo(
                            title = "Example"
                        ),
                        id = ""
                    )
                ),
                onRemoveButtonClick = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun FavoritesScreenEmptyPreview() {
    MyBooksTheme {
        Scaffold(
            topBar = {
                FavoritesAppBar()
            }
        ) { innerPadding ->
            FavoritesScreenContent(
                volumes = emptyList(),
                onRemoveButtonClick = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}