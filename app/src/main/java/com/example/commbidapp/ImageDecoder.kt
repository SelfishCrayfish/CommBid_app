package com.example.commbidapp

import android.util.Base64
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import android.graphics.BitmapFactory


fun decodeBase64ToImageBitmap(encodedString: String): ImageBitmap {
    val decodedBytes = Base64.decode(encodedString, Base64.DEFAULT)
    val bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    return bitmap.asImageBitmap()
}