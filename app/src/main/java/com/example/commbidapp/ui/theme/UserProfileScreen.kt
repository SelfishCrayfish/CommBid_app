package com.example.commbidapp.ui.theme

import android.content.Intent
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
import com.example.commbidapp.isArtistRequest
import com.example.commbidapp.usernameRequest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun UserProfileScreen(viewModel: WallViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val context = LocalContext.current
    val defaultUsername = stringResource(R.string.default_nickname)
    val defaultDescription = stringResource(R.string.your_description)
    var nickname by remember { mutableStateOf(UserSession.loggedUser.username) }
    var isEditingNickname by remember { mutableStateOf(false) }

    var description by remember { mutableStateOf(UserSession.loggedUser.about ?: "") }
    var isEditingDescription by remember { mutableStateOf(false) }

    var isArtistSwitchChecked by remember { mutableStateOf(UserSession.loggedUser.isArtist) }

    // Begin Column to include both the profile and wall screen
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Profile Section
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = R.drawable.profile_pic,
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(90.dp)
                    .clickable { /* TODO */ }
                    .clip(CircleShape),
                contentScale = ContentScale.FillBounds
            )

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
                                    // Handle success
                                    // dont care
                                } else {
                                    // Handle error response
                                }
                            }

                            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                                // Handle failure (e.g., network issue)
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
                                // Handle success
                                // dont care
                            } else {
                                // Handle error response
                            }
                        }

                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                            // Handle failure (e.g., network issue)
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
                            isArtistRequest(isArtistSwitchChecked)
                        ).enqueue(object : Callback<ResponseBody> {
                            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                                if (response.isSuccessful) {
                                    // Handle success
                                } else {
                                    // Handle error response
                                }
                            }

                            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                                // Handle failure (e.g., network issue)
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



//        Spacer(modifier = Modifier.height(16.dp))



//        Spacer(modifier = Modifier.height(32.dp))

        WallScreen(viewModel = viewModel,userId = UserSession.loggedUser.id)
    }
}
