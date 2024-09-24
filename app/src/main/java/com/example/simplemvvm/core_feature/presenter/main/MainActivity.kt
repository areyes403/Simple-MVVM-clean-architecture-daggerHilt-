package com.example.simplemvvm.core_feature.presenter.main

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.simplemvvm.core_feature.presenter.navigation.MainScreen
import com.example.simplemvvm.core_feature.presenter.navigation.NavigationRoutes
import com.example.simplemvvm.core_feature.presenter.navigation.authGraph
import com.example.simplemvvm.core_feature.presenter.theme.AppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setContent {
            AppTheme{
                Surface(
                    modifier = Modifier.fillMaxSize(),
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
    modifier:Modifier=Modifier,
    viewModel:MainActivityViewModel= hiltViewModel(),
    navHostController:NavHostController = rememberNavController()
){
    val mySessionState by viewModel.mySession.collectAsState()

    if (mySessionState==null){
        NavHost(
            modifier = modifier,
            navController = navHostController,
            startDestination = NavigationRoutes.Unauthenticated.NavigationRoute.route
        ){
            authGraph(navController = navHostController)
        }
    }else{
        MainScreen(navController = navHostController, modifier = modifier)
    }
}








