package dev.maruffirdaus.w2_androidstudygroup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dev.maruffirdaus.w2_androidstudygroup.ui.theme.W2_AndroidStudyGroupTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            W2_AndroidStudyGroupTheme(dynamicColor = false) {
                NavGraph()
            }
        }
    }
}