package com.example.simplemvvm.core_feature.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.simplemvvm.posts_feature.presenter.home.HomeScreen

@Composable
fun ButtonNavigation() {
    val navHost= rememberNavController()
    NavHost(
        navController = navHost,
        startDestination = ButtonNavigationScreen.Home.route
    ) {
        composable(ButtonNavigationScreen.Home.route) {
            HomeScreen()
        }
        composable(ButtonNavigationScreen.Profile.route) {
            //ProfileScreen()
        }
        composable(ButtonNavigationScreen.Settings.route) {
            /*SettingsScreen() {
                logout()
            }*/
        }
    }
}