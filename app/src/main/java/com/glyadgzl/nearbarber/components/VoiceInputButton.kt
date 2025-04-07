package com.glyadgzl.nearbarber.components

import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import java.util.jar.Manifest

@Composable
fun VoiceInputButton(
    onStartVoiceInput: () -> Unit
) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                onStartVoiceInput()
            } else {
                Toast.makeText(context, "Mikrofon izni reddedildi", Toast.LENGTH_SHORT).show()
            }
        }
    )

    Icon(
        imageVector = Icons.Default.Mic,
        contentDescription = "Voice",
        tint = Color.Gray,
        modifier = Modifier
            .size(20.dp)
            .clickable {
                when (PackageManager.PERMISSION_GRANTED) {
                    ContextCompat.checkSelfPermission(
                        context,
                        android.Manifest.permission.RECORD_AUDIO
                        //Manifest.permission.RECORD_AUDIO
                    ) -> {
                        onStartVoiceInput()
                    }

                    else -> {
                        launcher.launch(android.Manifest.permission.RECORD_AUDIO)
                    }
                }
            }
    )
}
