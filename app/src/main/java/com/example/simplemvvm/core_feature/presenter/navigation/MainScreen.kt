package com.example.simplemvvm.core_feature.presenter.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.simplemvvm.posts_feature.presenter.home.HomeScreen

@Composable
fun MainScreen(
    navController: NavHostController,
    modifier:Modifier
){
    Scaffold(
        modifier = modifier,
        bottomBar = {
            ButtonNavigation(navController)
        }
    ) { innerPadding ->
        NavHost(navController = navController, startDestination = BottomNavItem.Home.route, modifier = Modifier.padding(innerPadding)) {
            composable(BottomNavItem.Home.route) {
                HomeScreen()
            }
            composable(BottomNavItem.Search.route) {
                //SearchScreen()
            }
            composable(BottomNavItem.Profile.route) {
                //ProfileScreen()
            }
        }
    }
}