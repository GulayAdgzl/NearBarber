package com.glyadgzl.nearbarber.components

import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat

@Composable
fun VoiceInputWithSpeechRecognizer(
    onVoiceResult: (String) -> Unit
) {
    val context = LocalContext.current
    val speechHelper = remember { SpeechRecognizerHelper(context, onVoiceResult) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            if (granted) speechHelper.startListening()
            else Toast.makeText(context, "İzin gerekli", Toast.LENGTH_SHORT).show()
        }
    )

    Icon(
        imageVector = Icons.Default.Mic,
        contentDescription = "Mic",
        tint = Color.Gray,
        modifier = Modifier
            .size(20.dp)
            .clickable {
                if (ContextCompat.checkSelfPermission(context,                        android.Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                    speechHelper.startListening()
                } else {
                    launcher.launch(                       android.Manifest.permission.RECORD_AUDIO)
                }
            }
    )
}
