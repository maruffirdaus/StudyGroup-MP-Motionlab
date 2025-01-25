package dev.maruffirdaus.mybooks.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import dev.maruffirdaus.mybooks.R
import dev.maruffirdaus.mybooks.ui.theme.MyBooksTheme

@Composable
fun SearchDialog(
    onSearch: (String) -> Unit,
    onDismissRequest: () -> Unit
) {
    val context = LocalContext.current

    var query by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    fun dismissDialog() {
        onDismissRequest()
        query = ""
        errorMessage = ""
    }

    Dialog(
        onDismissRequest = {
            dismissDialog()
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(28.dp))
                .background(MaterialTheme.colorScheme.surfaceContainerHigh)
        ) {
            Spacer(
                modifier = Modifier.height(24.dp)
            )
            Text(
                text = stringResource(R.string.search_books),
                modifier = Modifier.padding(horizontal = 24.dp),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            OutlinedTextField(
                value = query,
                onValueChange = { value ->
                    query = value
                    errorMessage = ""
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                label = {
                    Text(
                        text = stringResource(R.string.keyword)
                    )
                },
                supportingText = {
                    if (errorMessage.isNotEmpty()) {
                        Text(
                            text = errorMessage
                        )
                    }
                },
                isError = errorMessage.isNotEmpty(),
                singleLine = true
            )
            Spacer(
                modifier = Modifier.height(24.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(
                    onClick = {
                        dismissDialog()
                    }
                ) {
                    Text(
                        text = stringResource(R.string.cancel)
                    )
                }
                Spacer(
                    modifier = Modifier.width(8.dp)
                )
                TextButton(
                    onClick = {
                        if (query.isNotEmpty()) {
                            onSearch(query)
                            dismissDialog()
                        } else {
                            errorMessage = context.getString(R.string.keyword_empty_message)
                        }
                    }
                ) {
                    Text(
                        text = stringResource(R.string.search)
                    )
                }
            }
            Spacer(
                modifier = Modifier.height(24.dp)
            )
        }
    }
}

@Preview
@Composable
private fun SearchDialogPreview() {
    MyBooksTheme {
        SearchDialog(
            onSearch = {},
            onDismissRequest = {}
        )
    }
}