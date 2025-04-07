import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore.Images.Media.getBitmap
import android.widget.Toast

import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.rounded.AddPhotoAlternate
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.glyadgzl.nearbarber.components.SpeechRecognizerHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    paddingValues: PaddingValues,
    uriState: MutableStateFlow<String>
) {
    val chaViewModel = viewModel<ChatViewModel>()
    val chatState = chaViewModel.chatState.collectAsState().value
    val bitmap = getbitmap(uriState)
    val context = LocalContext.current

    val speechHelper = remember {
        SpeechRecognizerHelper(context) { result ->
            chaViewModel.onEvent(ChatUiEvent.UpdatePrompt(result))
            chaViewModel.onEvent(ChatUiEvent.SendPrompt(result, bitmap))
            uriState.update { "" }
        }
    }


    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            if (granted) speechHelper.startListening()
            else Toast.makeText(context, "Mikrofon izni gerekli", Toast.LENGTH_SHORT).show()
        }
    )

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        uri?.let {
            uriState.update { it.toString() }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding()),
        verticalArrangement = Arrangement.Bottom
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            reverseLayout = true
        ) {
            itemsIndexed(chatState.chatList) { _, chat ->
                if (chat.isFromUser) {
                    UserChatItem(prompt = chat.prompt, bitmap = chat.bitmap)
                } else {
                    ModelChatItem(response = chat.prompt)
                }
            }
        }

        // ======= ALT GİRİŞ KISMI ========
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFF7EC)) // Açık bej arka plan
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Sol "+" butonu
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.White, shape = CircleShape)
                    .clickable {
                        imagePicker.launch(
                            PickVisualMediaRequest.Builder()
                                .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                .build()
                        )
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    tint = Color.Gray
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Metin alanı ve ses butonu
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(45.dp)
                    .background(Color.White, shape = RoundedCornerShape(24.dp)),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = chatState.prompt,
                        onValueChange = {
                            chaViewModel.onEvent(ChatUiEvent.UpdatePrompt(it))
                        },
                        placeholder = { Text("Type a message..") },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent
                        ),
                        modifier = Modifier.weight(1f),
                        maxLines = 1,
                        singleLine = true
                    )

                    // Mikrofon butonu
                    Icon(
                        imageVector = Icons.Default.GraphicEq,
                        contentDescription = "Voice",
                        tint = Color.Gray,
                        modifier = Modifier
                            .size(20.dp)
                            .clickable {
                                if (ContextCompat.checkSelfPermission(
                                        context,
                                        android.Manifest.permission.RECORD_AUDIO
                                    ) == PackageManager.PERMISSION_GRANTED
                                ) {
                                    speechHelper.startListening()
                                } else {
                                    permissionLauncher.launch( android.Manifest.permission.RECORD_AUDIO)
                                }
                            }
                    )
                    // Spacer to add space between the two icons
                    Spacer(modifier = Modifier.width(16.dp)) // Adjust the width as needed
                    Icon(
                        modifier = Modifier
                            .size(40.dp)
                            .clickable {
                                chaViewModel.onEvent(ChatUiEvent.SendPrompt(chatState.prompt, bitmap))
                                uriState.update { "" }
                            },
                        imageVector = Icons.Default.Send,
                        contentDescription = "Send prompt",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}


@Composable
fun UserChatItem(prompt: String, bitmap: Bitmap?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 64.dp, end = 8.dp, top = 8.dp)
            .wrapContentWidth(Alignment.End)
    ) {
        Text(
            text = prompt,
            color = Color.Black,
            fontSize = 16.sp,
            modifier = Modifier
                .background(Color(0xFFBEE3F8), RoundedCornerShape(16.dp))
                .padding(horizontal = 12.dp, vertical = 8.dp)
        )

        bitmap?.let {
            Spacer(modifier = Modifier.height(6.dp))
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = "user image",
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .height(200.dp)
                    .fillMaxWidth()
                    .background(Color.Gray),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun ModelChatItem(response: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 64.dp, top = 8.dp)
            .wrapContentWidth(Alignment.Start)
    ) {
        Text(
            text = response,
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier
                .background(Color(0xFF2D3748), RoundedCornerShape(16.dp))
                .padding(horizontal = 12.dp, vertical = 8.dp)
        )
    }
}

@Composable
private fun getbitmap(uriState: StateFlow<String>): Bitmap? {
    val uri = uriState.collectAsState().value

    val imageState: AsyncImagePainter.State = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(uri)
            .size(Size.ORIGINAL)
            .build()
    ).state

    return if (imageState is AsyncImagePainter.State.Success) {
        imageState.result.drawable.toBitmap()
    } else null
}@Preview(showBackground = true)
@Composable
fun SimpleChatScreenPreview() {
    val fakePrompt = "Merhaba, bu bir örnek mesaj!"
    val fakeBitmap: Bitmap? = null

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(8.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            reverseLayout = true
        ) {
            items(3) {
                UserChatItem(prompt = fakePrompt, bitmap = fakeBitmap)
                ModelChatItem(response = "Merhaba, size nasıl yardımcı olabilirim?")
            }
        }

        // Chat input area styled like the image
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFF7EC)) // Soft beige background
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // "+" icon
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.White, shape = CircleShape)
                    .clickable { },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    tint = Color.Gray
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Rounded input field with voice icon
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(45.dp)
                    .background(Color.White, shape = RoundedCornerShape(24.dp)),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Type a message..",
                        color = Color.Gray,
                        fontSize = 14.sp,
                        modifier = Modifier.weight(1f)
                    )

                    Icon(
                        imageVector = Icons.Default.GraphicEq, // Mikrofon benzeri ikon
                        contentDescription = "Voice",
                        tint = Color.Gray,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}
