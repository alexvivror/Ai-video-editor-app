package com.example.researchtovideo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.researchtovideo.theme.ResearchToVideoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ResearchToVideoTheme {
                // A surface container using the 'background' color from the theme
                AppNavigation()
            }
        }
    }
}