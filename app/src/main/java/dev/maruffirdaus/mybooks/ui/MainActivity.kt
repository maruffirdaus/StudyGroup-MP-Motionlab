package dev.maruffirdaus.mybooks.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dev.maruffirdaus.mybooks.ui.home.HomeScreen
import dev.maruffirdaus.mybooks.ui.theme.MyBooksTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyBooksTheme {
                HomeScreen()
            }
        }
    }
}