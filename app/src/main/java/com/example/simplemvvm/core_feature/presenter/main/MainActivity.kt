package com.example.simplemvvm.core_feature.presenter.main

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.example.simplemvvm.auth_feature.presenter.login.LoginScreen
import com.example.simplemvvm.auth_feature.presenter.register.RegisterScreen
import com.example.simplemvvm.core_feature.presenter.navigation.Screen
import com.example.simplemvvm.core_feature.presenter.theme.AppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setContent {
            AppTheme{
                Surface(
                    color = MaterialTheme.colorScheme.background
                ){
                    NavigationGraph()
                }
            }
        }
    }
}

@Composable
fun NavigationGraph(
    viewModel:MainActivityViewModel= hiltViewModel()
){
    val navHostController = rememberNavController()
    val mySessionState by viewModel.mySession.collectAsState()

    if (mySessionState==null){
        println("please SIGN IN")
        val graph = navHostController.createGraph(startDestination = Screen.Login.route){
            composable(
                route = Screen.Login.route,
                content = {
                    LoginScreen()
                }
            )
            composable(
                route = Screen.Register.route,
                content = {
                    RegisterScreen()
                }
            )
        }
        NavHost(navController = navHostController, graph = graph)
    }else{
        println("please SIGN OUT")
    }
}




