package com.example.commbidapp.ui.theme

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade

@Composable
fun CreatePostScreen(onPostAdded: (String, Uri?) -> Unit, onCancel: () -> Unit) {
    val context = LocalContext.current
    var postText by remember { mutableStateOf(TextFieldValue("")) }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "Wybierz zdjęcie",
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Button(
                onClick = { imagePickerLauncher.launch("image/*") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Załącz zdjęcie")
            }

            selectedImageUri?.let { uri ->
                Spacer(modifier = Modifier.height(16.dp))
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(uri)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Podgląd załączonego zdjęcia",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Treść posta",
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            TextField(
                value = postText,
                onValueChange = { if (it.text.length <= 140) postText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                placeholder = { Text("Wpisz treść posta...") },
                maxLines = 5
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = onCancel,
                modifier = Modifier.weight(1f)
            ) {
                Text("Anuluj")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {
                    if (postText.text.isNotBlank()) {
                        onPostAdded(postText.text, selectedImageUri)
                    } else {
                        Toast.makeText(
                            context,
                            "Treść posta nie może być pusta",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Dodaj post")
            }
        }
    }
}