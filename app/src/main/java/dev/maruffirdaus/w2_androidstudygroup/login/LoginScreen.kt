package dev.maruffirdaus.w2_androidstudygroup.login

import android.app.Activity
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import dev.maruffirdaus.w2_androidstudygroup.R
import dev.maruffirdaus.w2_androidstudygroup.data.Data

@Composable
fun LoginScreen(
    onLogin: (String) -> Unit,
    onRegister: () -> Unit
) {
    val activity = LocalView.current.context as Activity
    WindowCompat.getInsetsController(activity.window, activity.window.decorView).apply {
        isAppearanceLightStatusBars =
            activity.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK != Configuration.UI_MODE_NIGHT_YES
    }
    Scaffold(contentWindowInsets = WindowInsets.ime) { imePadding ->
        LoginContent(
            onLogin = onLogin,
            onRegister = onRegister,
            modifier = Modifier.padding(imePadding)
        )
    }
}

@Composable
fun LoginContent(
    onLogin: (String) -> Unit,
    onRegister: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var username by remember { mutableStateOf("") }
        var usernameSupportingText: String? by remember { mutableStateOf(null) }
        var isUsernameError by remember { mutableStateOf(false) }
        var password by remember { mutableStateOf("") }
        var passwordSupportingText: String? by remember { mutableStateOf(null) }
        var isPasswordError by remember { mutableStateOf(false) }
        var isPasswordVisible by remember { mutableStateOf(false) }
        val isLoginButtonEnabled = username.isNotEmpty() && password.isNotEmpty()
        Spacer(modifier = Modifier.statusBarsPadding())
        Spacer(modifier = Modifier.height(128.dp))
        Image(
            painter = painterResource(R.drawable.logo_red_transparent),
            contentDescription = null,
            modifier = Modifier
                .width(64.dp)
                .aspectRatio(1.0f)
        )
        Spacer(modifier = Modifier.height(128.dp))
        TextField(
            value = username,
            onValueChange = {
                username = it
                usernameSupportingText = null
                isUsernameError = false
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            label = {
                Text(text = "Username")
            },
            supportingText = usernameSupportingText?.let {
                { Text(text = it) }
            },
            isError = isUsernameError
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = password,
            onValueChange = {
                password = it
                passwordSupportingText = null
                isPasswordError = false
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            label = {
                Text(text = "Password")
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        isPasswordVisible = !isPasswordVisible
                    }
                ) {
                    if (isPasswordVisible) {
                        Icon(
                            painter = painterResource(R.drawable.ic_visibility_off),
                            contentDescription = null
                        )
                    } else {
                        Icon(
                            painter = painterResource(R.drawable.ic_visibility),
                            contentDescription = null
                        )
                    }
                }
            },
            supportingText = passwordSupportingText?.let {
                { Text(text = it) }
            },
            isError = isPasswordError,
            visualTransformation = if (isPasswordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            }
        )
        Spacer(modifier = Modifier.height(128.dp))
        Button(
            onClick = {
                if (!Data.users.containsKey(username)) {
                    usernameSupportingText = "Username belum terdaftar"
                    isUsernameError = true
                } else {
                    val user = Data.users[username]
                    user?.let {
                        if (password != it.password) {
                            passwordSupportingText = "Password salah"
                            isPasswordError = true
                        } else {
                            onLogin(it.username)
                        }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            enabled = isLoginButtonEnabled
        ) {
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.height(64.dp))
        Text(
            text = "Belum memiliki akun?",
            modifier = Modifier.padding(horizontal = 32.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(
            onClick = { onRegister() }
        ) {
            Text(text = "Daftar")
        }
        Spacer(modifier = Modifier.height(32.dp))
        Spacer(modifier = Modifier.navigationBarsPadding())
    }
}