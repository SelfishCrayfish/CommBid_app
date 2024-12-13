package com.example.commbidapp.ui.theme

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import coil3.compose.AsyncImage
import com.example.commbidapp.CreatePostActivity
import com.example.commbidapp.DescriptionRequest
import com.example.commbidapp.R
import com.example.commbidapp.RetrofitInstance
import com.example.commbidapp.SomeoneProfileActivity
import com.example.commbidapp.UserSession
import com.example.commbidapp.WallViewModel
import com.example.commbidapp.ArtistRequest
import com.example.commbidapp.ImgurApiService
import com.example.commbidapp.PfpRequest
import com.example.commbidapp.decodeImageUrlToImageBitmap
import com.example.commbidapp.usernameRequest
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



@Composable
fun UserProfileScreen(viewModel: WallViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope() // Remember a coroutine scope for launching coroutines
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }


    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {  uri: Uri? ->
        selectedImageUri = uri
            if (selectedImageUri != null) {
                coroutineScope.launch {
                    try {
                        // Call the suspend function for image upload
                        val uploadedImageUrl = uploadImageToImgur(selectedImageUri!!, context)
                        println(uploadedImageUrl)
                        UserSession.loggedUser.id?.let { userId ->
                            uploadedImageUrl?.let { imageUrl: String ->
                                val pfpRequest = PfpRequest(imageUrl)

                                // Make the Retrofit call asynchronously using enqueue
                                RetrofitInstance.userService.changePfp(userId, pfpRequest)
                                    .enqueue(object : Callback<ResponseBody> {
                                        override fun onResponse(
                                            call: Call<ResponseBody>,
                                            response: Response<ResponseBody>
                                        ) {
                                            if (response.isSuccessful) {
                                                // Handle success
                                                val responseBody = response.body()
                                                responseBody?.let {
                                                    println("Profile picture updated successfully: ${it.string()}")
                                                }
                                            } else {
                                                // Handle error response
                                                val errorBody = response.errorBody()?.string()
                                                println("Error updating profile picture: $errorBody")
                                            }
                                        }

                                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                                            // Handle failure, e.g., show a toast or log the error
                                            t.printStackTrace()
                                            println("Failed to update profile picture: ${t.message}")
                                        }
                                    })
                            }
                        }
                    } catch (e: Exception) {
                        // Handle exceptions from the upload function
                        e.printStackTrace()
                        println("Failed to upload image: ${e.message}")
                    }
                }
            }

    }



    fun onProfileImageClick() {
        // Launch the image picker
        launcher.launch("image/*") // Trigger the image picker with image MIME type
    }
    val defaultUsername = stringResource(R.string.default_nickname)
    val defaultDescription = stringResource(R.string.your_description)
    var nickname by remember { mutableStateOf(UserSession.loggedUser.username) }
    var isEditingNickname by remember { mutableStateOf(false) }

    var description by remember { mutableStateOf(UserSession.loggedUser.about ?: "") }
    var isEditingDescription by remember { mutableStateOf(false) }

    var isArtistSwitchChecked by remember { mutableStateOf(UserSession.loggedUser.artist) }



    // State for storing the decoded image bitmap
    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }

    // Load image asynchronously using LaunchedEffect to call the suspend function
    LaunchedEffect(UserSession.loggedUser.profilePicture) {
        val profilePictureUrl = UserSession.loggedUser.profilePicture
        profilePictureUrl?.let {
            imageBitmap = decodeImageUrlToImageBitmap(it) // Call suspend function here
        }
    }
    // Begin Column to include both the profile and wall screen
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Profile Section
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            imageBitmap?.let {
                Image(
                    bitmap = it,
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(90.dp)
                        .clickable { onProfileImageClick() }
                        .clip(CircleShape),
                    contentScale = ContentScale.FillBounds
                )
            } ?: run {
                // Fallback image if imageBitmap is null
                Image(
                    painter = painterResource(id = R.drawable.profile_pic),
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(90.dp)
                        .clickable { onProfileImageClick() }
                        .clip(CircleShape),
                    contentScale = ContentScale.FillBounds
                )
            }
        }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                if (isEditingNickname) {
                    OutlinedTextField(
                        value = nickname,
                        onValueChange = { newNickname -> nickname = newNickname },
                        label = { Text(stringResource(id = R.string.nickname)) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                } else {
                    Text(
                        text = nickname,
                        fontSize = 22.sp,
                        color = Color.Black
                    )
                }

                Text(
                    text = stringResource(id = R.string.default_username),
                    color = Color.Gray
                )
            }

            IconButton(onClick = {
                isEditingNickname = !isEditingNickname
                if (!isEditingNickname)
                {
                    UserSession.loggedUser.id?.let { userId ->
                        RetrofitInstance.userService.changeUsername(
                            userId,
                            usernameRequest(nickname)
                        ).enqueue(object : Callback<ResponseBody> {
                            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                                if (response.isSuccessful) {
                                    UserSession.loggedUser.username = nickname
                                } else {
                                    nickname = UserSession.loggedUser.username
                                }
                            }

                            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                                nickname = UserSession.loggedUser.username
                                Toast.makeText(context,"no internet connection",Toast.LENGTH_SHORT).show()
                            }
                        })
                    }
                }
            }) {
                if (isEditingNickname) {
                    Icon(
                        painter = painterResource(id = R.drawable.save_pic),
                        contentDescription = "Save Nickname"
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.edit_pic),
                        contentDescription = "Edit Nickname"
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            if (isEditingDescription) {
                OutlinedTextField(
                    value = description,
                    onValueChange = { newDescription -> description = newDescription },
                    label = { Text(stringResource(id = R.string.description)) },
                    modifier = Modifier
                        .weight(1f)
                        .height(150.dp)
                )
            } else {
                Text(
                    text = description,
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 16.dp)
                )
            }

            IconButton(onClick = {
                isEditingDescription = !isEditingDescription

                if (!isEditingDescription)
                { UserSession.loggedUser.id?.let { userId ->
                    RetrofitInstance.userService.changeDescription(
                        userId,
                        DescriptionRequest(description)
                    ).enqueue(object : Callback<ResponseBody> {
                        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                            if (response.isSuccessful) {
                                UserSession.loggedUser.about = description
                            } else {
                                description = UserSession.loggedUser.about ?: ""
                            }
                        }

                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                            description = UserSession.loggedUser.about ?: ""
                            Toast.makeText(context,"no internet connection",Toast.LENGTH_SHORT).show()
                        }
                    })
                }}

            }) {
                if (isEditingDescription) {
                    Icon(
                        painter = painterResource(id = R.drawable.save_pic),
                        contentDescription = "Save Description"
                    )
                } else {

                    Icon(
                        painter = painterResource(id = R.drawable.edit_pic),
                        contentDescription = "Edit Description"


                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Switch(
                checked = isArtistSwitchChecked,
                onCheckedChange = {
                    isArtistSwitchChecked = it
                    UserSession.loggedUser.id?.let { userId ->
                        RetrofitInstance.userService.changeIsArtist(
                            userId,
                            ArtistRequest(isArtistSwitchChecked)
                        ).enqueue(object : Callback<ResponseBody> {
                            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                                if (response.isSuccessful) {
                                    UserSession.loggedUser.artist = isArtistSwitchChecked

                                } else {
                                    isArtistSwitchChecked = UserSession.loggedUser.artist
                                }
                            }

                            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                                isArtistSwitchChecked = UserSession.loggedUser.artist
                                Toast.makeText(context,"no internet connection",Toast.LENGTH_SHORT).show()
                            }
                        })
                    }
                                  },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Black,
                    uncheckedThumbColor = Color.Gray
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(id = R.string.artist_switch),
                color = Color.Black,
                fontSize = 18.sp,
                modifier = Modifier.weight(1f) // Wypełnia przestrzeń między przełącznikiem a nowymi elementami
            )

            if (isArtistSwitchChecked) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.add_post),
                        color = Color.Black,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    IconButton(
                        onClick = {
                            context.startActivity(Intent(context, CreatePostActivity::class.java))
                        },
                        modifier = Modifier.align(Alignment.CenterVertically)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.plus_icon),
                            contentDescription = "Add Work",
                            tint = Color.Black,
                            modifier = Modifier.size(48.dp)
                        )
                    }
                }
            }
        }
        WallScreen(viewModel = viewModel,userId = UserSession.loggedUser.id)
    }

