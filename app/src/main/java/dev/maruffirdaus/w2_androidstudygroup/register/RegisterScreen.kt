package dev.maruffirdaus.w2_androidstudygroup.register

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import dev.maruffirdaus.w2_androidstudygroup.R
import dev.maruffirdaus.w2_androidstudygroup.data.Data
import dev.maruffirdaus.w2_androidstudygroup.data.User

@Composable
fun RegisterScreen(onBack: () -> Unit) {
    Scaffold(contentWindowInsets = WindowInsets.ime) { imePadding ->
        RegisterContent(
            onBack = onBack,
            modifier = Modifier.padding(imePadding)
        )
    }
}

@Composable
fun RegisterContent(
    onBack: () -> Unit,
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
        var email by remember { mutableStateOf("") }
        var emailSupportingText: String? by remember { mutableStateOf(null) }
        var isEmailError by remember { mutableStateOf(false) }
        var password by remember { mutableStateOf("") }
        var confirmPassword by remember { mutableStateOf("") }
        var confirmPasswordSupportingText: String? by remember { mutableStateOf(null) }
        var isConfirmPasswordError by remember { mutableStateOf(false) }
        var isPasswordVisible by remember { mutableStateOf(false) }
        var isConfirmPasswordVisible by remember { mutableStateOf(false) }
        val isRegisterButtonEnabled =
            username.isNotEmpty() && email.isNotEmpty() && !isEmailError && password.isNotEmpty() && confirmPassword.isNotEmpty() && !isConfirmPasswordError
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
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            label = {
                Text(text = "Username")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = email,
            onValueChange = {
                email = it
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailSupportingText = "Format email tidak valid"
                    isEmailError = true
                } else {
                    emailSupportingText = null
                    isEmailError = false
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            label = {
                Text(text = "Email")
            },
            supportingText = emailSupportingText?.let {
                { Text(text = it) }
            },
            isError = isEmailError,
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = password,
            onValueChange = {
                password = it
                if (password != confirmPassword) {
                    confirmPasswordSupportingText = "Password tidak cocok"
                    isConfirmPasswordError = true
                } else {
                    confirmPasswordSupportingText = null
                    isConfirmPasswordError = false
                }
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
            visualTransformation = if (isPasswordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                if (password != confirmPassword) {
                    confirmPasswordSupportingText = "Password tidak cocok"
                    isConfirmPasswordError = true
                } else {
                    confirmPasswordSupportingText = null
                    isConfirmPasswordError = false
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            label = {
                Text(text = "Confirm password")
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        isConfirmPasswordVisible = !isConfirmPasswordVisible
                    }
                ) {
                    if (isConfirmPasswordVisible) {
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
            supportingText = confirmPasswordSupportingText?.let {
                { Text(text = it) }
            },
            isError = isConfirmPasswordError,
            visualTransformation = if (isConfirmPasswordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            }
        )
        Spacer(modifier = Modifier.height(128.dp))
        Row(modifier = Modifier.padding(horizontal = 32.dp)) {
            FilledTonalButton(
                onClick = { onBack() },
                modifier = Modifier.weight(1.0f)
            ) {
                Text(text = "Batal")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {
                    Data.users += Pair(
                        username,
                        User(
                            username,
                            email,
                            password
                        )
                    )
                    onBack()
                },
                modifier = Modifier.weight(1.0f),
                enabled = isRegisterButtonEnabled
            ) {
                Text(text = "Daftar")
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Spacer(modifier = Modifier.navigationBarsPadding())
    }
}