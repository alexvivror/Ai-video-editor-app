package com.example.researchtovideo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.researchtovideo.ui.editor.EditorScreen
import com.example.researchtovideo.ui.export.ExportScreen
import com.example.researchtovideo.ui.home.HomeScreen
import com.example.researchtovideo.ui.new_video.NewVideoScreen
import com.example.researchtovideo.ui.video_setup.VideoSetupScreen

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController)
        }
        composable("new_video") {
            NewVideoScreen(navController)
        }
        composable("video_setup") {
            VideoSetupScreen(navController)
        }
        composable("editor") {
            EditorScreen(navController)
        }
        composable("export") {
            ExportScreen(navController)
        }
    }
}