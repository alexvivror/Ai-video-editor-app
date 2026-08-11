package com.example.researchtovideo.ui.new_video

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun NewVideoScreen(navController: NavHostController) {
    var sourceText by remember { mutableStateOf("") }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Create New Video",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Paste a URL, PDF path or research text as your source.",
                style = MaterialTheme.typography.bodyMedium
            )
            OutlinedTextField(
                value = sourceText,
                onValueChange = { sourceText = it },
                label = { Text("Research source (URL / text)") },
                modifier = Modifier.fillMaxSize()
            )
            Button(
                onClick = { navController.navigate("video_setup") },
                enabled = sourceText.isNotBlank()
            ) {
                Text("Next: Configure")
            }
        }
    }
}
