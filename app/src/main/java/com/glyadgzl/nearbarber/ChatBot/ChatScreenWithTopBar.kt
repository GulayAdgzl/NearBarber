package com.glyadgzl.nearbarber.ChatBot

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ChatScreenWithTopBar(
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Text(text = "ChatScreenWithTopBar")
    }
}

@Preview(name = "ChatScreenWithTopBar")
@Composable
private fun PreviewChatScreenWithTopBar() {
    ChatScreenWithTopBar()
}