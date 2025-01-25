package dev.maruffirdaus.mybooks.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.maruffirdaus.mybooks.R
import dev.maruffirdaus.mybooks.data.model.Volume
import dev.maruffirdaus.mybooks.data.model.VolumeInfo
import dev.maruffirdaus.mybooks.data.remote.response.VolumesResponse
import dev.maruffirdaus.mybooks.data.remote.retrofit.ApiConfig
import dev.maruffirdaus.mybooks.ui.theme.MyBooksTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val context = LocalContext.current

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    var isLoading by remember { mutableStateOf(false) }
    var volumes by remember { mutableStateOf(emptyList<Volume>()) }
    var message: String? by remember { mutableStateOf(null) }

    var isSearchDialogVisible by remember { mutableStateOf(false) }

    fun getVolumes(query: String) {
        isLoading = true

        val client = ApiConfig.getApiService().getVolumes(query)

        client.enqueue(object : Callback<VolumesResponse> {
            override fun onResponse(
                call: Call<VolumesResponse>,
                response: Response<VolumesResponse>
            ) {
                isLoading = false
                if (response.isSuccessful) {
                    val responseBody = response.body()

                    if (responseBody != null) {
                        volumes = responseBody.items
                        if (responseBody.totalItems == 0) {
                            message = context.getString(R.string.keyword_unmatch_message)
                        }
                    }
                } else {
                    volumes = emptyList()
                    message = response.message()
                }
            }

            override fun onFailure(call: Call<VolumesResponse>, response: Throwable) {
                isLoading = false
                volumes = emptyList()
                message = response.message.toString()
            }
        })
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            HomeAppBar(
                onSearchButtonClick = {
                    isSearchDialogVisible = true
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        HomeScreenContent(
            volumes = volumes,
            onSearchButtonClick = {
                isSearchDialogVisible = true
            },
            modifier = Modifier.padding(innerPadding),
            loading = isLoading,
            message = message
        )

        if (isSearchDialogVisible) {
            SearchDialog(
                onSearch = { query ->
                    getVolumes(query)
                },
                onDismissRequest = {
                    isSearchDialogVisible = false
                }
            )
        }
    }
}

@Composable
fun HomeScreenContent(
    volumes: List<Volume>,
    onSearchButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    message: String? = null
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
                    text = message ?: stringResource(R.string.welcome_message),
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(
                    modifier = Modifier.height(32.dp)
                )
                OutlinedButton(
                    onClick = onSearchButtonClick
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_search),
                        contentDescription = stringResource(R.string.search_books)
                    )
                    Spacer(
                        modifier = Modifier.width(8.dp)
                    )
                    Text(
                        text = stringResource(R.string.search_books)
                    )
                }
            }
        }

        else -> {
            LazyColumn(
                modifier = modifier.fillMaxSize()
            ) {
                item {
                    Spacer(
                        modifier = Modifier.width(8.dp)
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
                item {
                    Spacer(
                        modifier = Modifier.width(8.dp)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun HomeScreenPreview() {
    MyBooksTheme {
        Scaffold(
            topBar = {
                HomeAppBar(
                    onSearchButtonClick = {}
                )
            }
        ) { innerPadding ->
            HomeScreenContent(
                volumes = listOf(
                    Volume(
                        volumeInfo = VolumeInfo(
                            title = "Example"
                        ),
                        id = ""
                    )
                ),
                onSearchButtonClick = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun HomeScreenEmptyPreview() {
    MyBooksTheme {
        Scaffold(
            topBar = {
                HomeAppBar(
                    onSearchButtonClick = {}
                )
            }
        ) { innerPadding ->
            HomeScreenContent(
                volumes = emptyList(),
                onSearchButtonClick = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun HomeScreenEmptyWithMessagePreview() {
    MyBooksTheme {
        Scaffold(
            topBar = {
                HomeAppBar(
                    onSearchButtonClick = {}
                )
            }
        ) { innerPadding ->
            HomeScreenContent(
                volumes = emptyList(),
                onSearchButtonClick = {},
                modifier = Modifier.padding(innerPadding),
                message = stringResource(R.string.keyword_unmatch_message)
            )
        }
    }
}