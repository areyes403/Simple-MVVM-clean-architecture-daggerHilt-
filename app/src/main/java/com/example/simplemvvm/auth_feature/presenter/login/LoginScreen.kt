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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.simplemvvm.core_feature.domain.model.APIResponse
import com.example.simplemvvm.core_feature.domain.model.UIState


@Composable
fun LoginScreen(
    viewModel:LoginScreenViewModel = hiltViewModel(),
    navigateToRegistration:()->Unit
){
    val userEmail = remember { mutableStateOf("") }
    val userPassword = remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }
    val response by viewModel.loginResponse.observeAsState()

    Scaffold(
        snackbarHost = {
            //SnackbarHost(hostState = snackBarHost)
        }
    ) { content->
        Column(
            modifier = Modifier
                .padding(content)
                .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 0.dp)
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
                    .padding(0.dp, 8.dp, 0.dp, 0.dp)
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
                    .padding(0.dp, 8.dp, 0.dp, 0.dp),
                visualTransformation = PasswordVisualTransformation()
            )

            // Login button
            OutlinedButton(
                onClick = {
                    loading=true
                    viewModel.login(email = userEmail.value, password = userPassword.value)
                          },
                enabled = !loading,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 25.dp, 0.dp, 0.dp)
            ) {
                if (loading){
                    CircularProgressIndicator(
                        modifier = Modifier.width(64.dp),
                        color = MaterialTheme.colorScheme.secondary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    )
                }else{
                    Text(text = "Login",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp
                    )
                }

            }
        }
        when(response){
            is APIResponse.Success->{
                Log.i("loginState","Success")
                loading=false
            }
            is APIResponse.Error->{
                loading=false
                Log.i("loginState","Error")
            }
            else->{
                Log.i("loginState","null")

            }
        }
    }

}