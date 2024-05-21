@file:OptIn(ExperimentalMaterial3Api::class)

package com.sparklead.petsync.ui.profile

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ProfileScreen(profileViewModel: ProfileViewModel = hiltViewModel()) {


    var showTimeDialog: Boolean by rememberSaveable { mutableStateOf(false) }
    val timePickerState = rememberTimePickerState()


    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = White),
            title = {
                Text(
                    text = "schedule Feed",
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Medium,
                        fontStyle = FontStyle.Normal
                    ),
                    color = Black,
                    textAlign = TextAlign.Center
                )
            }
        )
    }) { padding ->
        if (showTimeDialog) {
            TimePickerDialog(
                onDismissRequest = { /*TODO*/ },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showTimeDialog = false
                            Log.e("@@@@-h", timePickerState.hour.toString())
                            Log.e("@@@@-m", timePickerState.minute.toString())
                            Log.e("@@@@-h", timePickerState.toString())
                        }
                    ) { Text("OK") }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showTimeDialog = false
                        }
                    ) { Text("Cancel") }
                },
                containerColor = White
            )
            {
                TimePicker(state = timePickerState)
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable(onClick = {
                        showTimeDialog = true
                    }),
                shape = RoundedCornerShape(4.dp),
                colors = CardDefaults.cardColors(White)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = profileViewModel.alarmTime1,
                        modifier = Modifier.padding(start = 8.dp),
                        fontSize = 18.sp
                    )
                    Switch(
                        checked = profileViewModel.checked1,
                        onCheckedChange = {
                            profileViewModel.checked1 = profileViewModel.checked1.not()
                        })
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable(onClick = {
                        showTimeDialog = true
                    }),
                shape = RoundedCornerShape(4.dp),
                colors = CardDefaults.cardColors(White)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = profileViewModel.alarmTime2,
                        modifier = Modifier.padding(start = 8.dp),
                        fontSize = 18.sp
                    )
                    Switch(
                        checked = profileViewModel.checked2,
                        onCheckedChange = {
                            profileViewModel.checked2 = profileViewModel.checked2.not()
                        })
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable(onClick = {
                        showTimeDialog = true
                    }),
                shape = RoundedCornerShape(4.dp),
                colors = CardDefaults.cardColors(White)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = profileViewModel.alarmTime3,
                        modifier = Modifier.padding(start = 8.dp),
                        fontSize = 18.sp
                    )
                    Switch(
                        checked = profileViewModel.checked3,
                        onCheckedChange = {
                            profileViewModel.checked3 = profileViewModel.checked3.not()
                        })
                }
            }
        }
    }

}


@Composable
fun TimePickerDialog(
    title: String = "Select Time",
    onDismissRequest: () -> Unit,
    confirmButton: @Composable (() -> Unit),
    dismissButton: @Composable (() -> Unit)? = null,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
    ) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            tonalElevation = 6.dp,
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min)
                .background(
                    shape = MaterialTheme.shapes.extraLarge,
                    color = containerColor
                ),
            color = containerColor
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    text = title,
                    style = MaterialTheme.typography.labelMedium
                )
                content()
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    dismissButton?.invoke()
                    confirmButton()
                }
            }
        }
    }
}