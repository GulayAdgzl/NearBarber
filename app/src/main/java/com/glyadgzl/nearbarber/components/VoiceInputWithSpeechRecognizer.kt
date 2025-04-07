package com.glyadgzl.nearbarber.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun VoiceInputWithSpeechRecognizer(
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Text(text = "VoiceInputWithSpeechRecognizer")
    }
}

@Preview(name = "VoiceInputWithSpeechRecognizer")
@Composable
private fun PreviewVoiceInputWithSpeechRecognizer() {
    VoiceInputWithSpeechRecognizer()
}