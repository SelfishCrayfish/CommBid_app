package com.example.commbidapp.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.commbidapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommissionScreen() {
    var artDescription by remember { mutableStateOf("") }
    var extraDetails by remember { mutableStateOf("") }

    val artStyles = listOf(
        stringResource(id = R.string.artstyle_realism),
        stringResource(id = R.string.artstyle_cartoon),
        stringResource(id = R.string.artstyle_abstract),
        stringResource(id = R.string.artstyle_impressionism)
    )
    var expanded by remember { mutableStateOf(false) }
    var selectedArtStyle by remember { mutableStateOf(artStyles[0]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = stringResource(id = R.string.choose_desired_artstyle),
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    readOnly = true,
                    value = selectedArtStyle,
                    onValueChange = { },
                    label = { Text(stringResource(id = R.string.artstyle_name)) },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    artStyles.forEach { style ->
                        DropdownMenuItem(
                            text = { Text(style) },
                            onClick = {
                                selectedArtStyle = style
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.describe_in_detail_text),
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = artDescription,
                onValueChange = { artDescription = it },
                placeholder = { Text(stringResource(id = R.string.scene_characters_etc)) },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 5
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.give_any_extra_detail),
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = extraDetails,
                onValueChange = { extraDetails = it },
                placeholder = { Text(stringResource(id = R.string.color_pref_background_etc)) },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 3
            )
        }

        CustomButton(
            text = stringResource(id = R.string.confirm_and_choose_payment),
            onClick = { },
            fontFamily = RegularFont,
            backgroundColor = BlueCrayolaColor,
            modifier = Modifier.width(450.dp).height(100.dp)
        )
    }
}
