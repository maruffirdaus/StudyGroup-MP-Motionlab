package dev.maruffirdaus.w3_androidstudygroup.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dev.maruffirdaus.w3_androidstudygroup.ui.theme.StoryspaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StoryspaceTheme(dynamicColor = false) {
                MainScreen()
            }
        }
    }
}