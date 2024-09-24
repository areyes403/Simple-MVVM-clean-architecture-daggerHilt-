package com.example.simplemvvm.core_feature.presenter.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.simplemvvm.auth_feature.presenter.login.LoginScreen
import com.example.simplemvvm.auth_feature.presenter.register.RegisterScreen

fun NavGraphBuilder.authGraph(navController:NavController){
    navigation(
        route = NavigationRoutes.Unauthenticated.NavigationRoute.route,
        startDestination = NavigationRoutes.Unauthenticated.Login.route
    ){
        composable(route = NavigationRoutes.Unauthenticated.Login.route) {
            LoginScreen(
                navigateToRegistration = {
                    //navController.navigate(route = NavigationRoutes.Unauthenticated.Registration.route)
                }
            )
        }
        composable(route = NavigationRoutes.Unauthenticated.Registration.route,) {
            RegisterScreen(
                onBackPressed = {
                    //navController.navigateUp()
                }
            )
        }
    }
}

