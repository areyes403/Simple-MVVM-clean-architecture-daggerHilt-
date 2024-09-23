package com.example.simplemvvm.auth_feature.presenter.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.simplemvvm.core_feature.domain.model.UIState
import kotlinx.coroutines.launch
import kotlin.math.log

@Composable
fun LoginScreen(
    snackBarHost:SnackbarHostState,
    viewModel:LoginScreenViewModel = hiltViewModel()
){
    val userEmail = remember { mutableStateOf("") }
    val userPassword = remember { mutableStateOf("") }
    val loginState by remember { derivedStateOf { viewModel.loginResponse } }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHost)
        }
    ) { content->
        Column(
            modifier = Modifier.padding(content)
        ){
            // Welcome message
            Text(text = "Hello,\nWelcome to the login page", fontSize = 25.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 50.dp, 0.dp, 0.dp)
            )

            // Username input field
            OutlinedTextField(value = userEmail.value, onValueChange = {
                userEmail.value = it
            },
                leadingIcon = {
                    Icon(Icons.Default.Person, contentDescription = "person")
                },
                label = {
                    Text(text = "email")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 20.dp, 0.dp, 0.dp)
            )

            // Password input field
            OutlinedTextField(value = userPassword.value, onValueChange = {
                userPassword.value = it
            },
                leadingIcon = {
                    Icon(Icons.Default.Info, contentDescription = "password")
                },
                label = {
                    Text(text = "password")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 20.dp, 0.dp, 0.dp),
                visualTransformation = PasswordVisualTransformation()
            )

            // Login button
            OutlinedButton(onClick = {
                viewModel.login(email = userEmail.value, password = userPassword.value)
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 25.dp, 0.dp, 0.dp)) {
                Text(text = "Login",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }
        }
        when(loginState){
            is UIState.Loading->{
                Log.i("loginState","Loading")

            }
            is UIState.Success->{
                Log.i("loginState","Success")
            }
            is UIState.Error->{
                Log.e("loginState", (loginState as UIState.Error).message)
            }
        }
    }

}