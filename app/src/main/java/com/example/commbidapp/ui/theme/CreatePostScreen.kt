package com.example.commbidapp.ui.theme

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.commbidapp.ImgurRetrofitInstance
import com.example.commbidapp.LoginActivity
import com.example.commbidapp.Post
import com.example.commbidapp.R
import com.example.commbidapp.UserSession
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileOutputStream

fun copyStreamToFile(uri: Uri,contentResolver: ContentResolver): File {
    val outputFile = File.createTempFile("temp", null)

    contentResolver.openInputStream(uri)?.use { input ->
        val outputStream = FileOutputStream(outputFile)
        outputStream.use { output ->
            val buffer = ByteArray(4 * 1024) // buffer size
            while (true) {
                val byteCount = input.read(buffer)
                if (byteCount < 0) break
                output.write(buffer, 0, byteCount)
            }
            output.flush()
        }
    }
    return outputFile
}


suspend fun uploadImageToImgur(uri: Uri,context : Context): String? {
    val contentResolver: ContentResolver = context.contentResolver

    // Convert URI to File
    val file = copyStreamToFile(uri, contentResolver)
    val requestBody = RequestBody.create("image/*".toMediaTypeOrNull(), file)
    val multipartBody = MultipartBody.Part.createFormData("image", file.name, requestBody)

    val response = ImgurRetrofitInstance.imgurApiService.uploadImage(
        authorization = "Client-ID 3c31d57f7c8dab3", // Replace with your Imgur Client ID
        image = multipartBody
    )

    return if (response.isSuccessful) {
        response.body()?.data?.link // Return the image URL from Imgur
    } else {
        null
    }
}
@Composable
fun CreatePostScreen(onPostAdded: (post : Post) -> Unit, onCancel: () -> Unit) {


    val coroutineScope = rememberCoroutineScope() // Get the coroutine scope


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
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomButton(
                text = stringResource(id = R.string.choose_picture),
                onClick = {
                    imagePickerLauncher.launch("image/*")
                },
                fontFamily = RegularFont,
                backgroundColor = BlueCrayolaColor,
                modifier = Modifier.width(250.dp),
            )

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

            Spacer(modifier = Modifier.height(32.dp))

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
            CustomButton(
                text = stringResource(id = R.string.cancel),
                onClick = {
                    onCancel()
                },
                fontFamily = RegularFont,
                backgroundColor = BlueCrayolaColor,
                modifier = Modifier.width(180.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            CustomButton(
                text = stringResource(id = R.string.add_post),
                onClick = {
                    val imageUri = selectedImageUri

                    if (postText.text.isNotBlank()) {
                        // Use LaunchedEffect to run the image upload and post addition asynchronously
                        coroutineScope.launch {
                            val uploadedImageLink = imageUri?.let { uri ->
                                uploadImageToImgur(uri,context)
                            } ?: "https://i.imgur.com/9iNdYSJ.jpeg" // Placeholder image if no image is selected

                            val post = Post(
                                title = "Default Title", // Replace with actual title if needed
                                body = postText.text,
                                image = uploadedImageLink,
                                user = UserSession.loggedUser
                            )
                            onPostAdded(post)
                        }
                    } else {
                        Toast.makeText(
                            context,
                            R.string.post_must_have_text,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                fontFamily = RegularFont,
                backgroundColor = BlueCrayolaColor,
                modifier = Modifier.width(180.dp)
            )
        }
    }


}