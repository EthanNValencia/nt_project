package com.nt.nt_notify

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties

// Had to pull this out of the MainActivity because of clashing imports.
// Kotlin forces me to use more files due to having so many imports with
// the same name.
@Composable
fun LoginAccountDialogue(dialogOpen: MutableState<Boolean>, mainViewModel: MainViewModel) {
    val email = remember{ mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val ntWhite = colorResource(id = R.color.nt_white)
    val ntMaroon = colorResource(id = R.color.nt_maroon)
    val ntDarkGray = colorResource(id = R.color.nt_dark_grey)
    val ntSalmon = colorResource(id = R.color.nt_salmon)

    fun validateInputs() {
        // dialogOpen.value = false
        mainViewModel.authenticate(UserCredentials(username = email.value, password = password.value))
        Log.d("auth", mainViewModel.authState.value.toString())
    }

    if(dialogOpen.value) {
        AlertDialog(
            backgroundColor = colorResource(id = R.color.nt_light_grey),
            onDismissRequest = { dialogOpen.value = false },
            confirmButton = {
                Button(onClick = { validateInputs() }, colors = ButtonDefaults.buttonColors(backgroundColor = ntMaroon)) {
                    Text(text = "Confirm", fontWeight = FontWeight.ExtraBold,  color = ntWhite)
                }
            }, dismissButton = {
                TextButton(onClick = { dialogOpen.value = false }, colors = ButtonDefaults.buttonColors(backgroundColor = ntMaroon)) {
                    Text(text = "Dismiss", fontWeight = FontWeight.ExtraBold, color = ntWhite)
                }
            }, title = {
                Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
                    Text(text = "Login", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
                }
            }, text = {
                Column(modifier = Modifier
                    .wrapContentSize()
                    .padding(0.dp), verticalArrangement = Arrangement.Center) {
                    TextField(value = email.value, onValueChange = { email.value = it }, modifier = Modifier.padding(top = 16.dp),
                        label = { Text(text = "Email", color = ntDarkGray)},
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = ntSalmon,
                            unfocusedIndicatorColor = ntSalmon,
                            cursorColor = ntSalmon))
                    TextField(value = password.value, onValueChange = { password.value = it }, modifier = Modifier.padding(top = 8.dp),
                        label = { Text(text = "Password", color = ntDarkGray)},
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = ntSalmon,
                            unfocusedIndicatorColor = ntSalmon,
                            cursorColor = ntSalmon),
                        visualTransformation = PasswordVisualTransformation()
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(ntMaroon)
                .padding(8.dp),
            shape = RoundedCornerShape(5.dp),
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = false)
        )
    }
}