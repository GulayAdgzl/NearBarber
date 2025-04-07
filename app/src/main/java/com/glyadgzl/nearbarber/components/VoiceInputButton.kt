package com.glyadgzl.nearbarber.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun VoiceInputButton(
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Text(text = "VoiceInputButton")
    }
}

@Preview(name = "VoiceInputButton")
@Composable
private fun PreviewVoiceInputButton() {
    VoiceInputButton()
}