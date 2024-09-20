package com.example.simplemvvm.core_feature.presenter.navigation

import com.example.simplemvvm.core_feature.util.Constants.Companion.LOGIN_SCREEN
import com.example.simplemvvm.core_feature.util.Constants.Companion.REGISTER_SCREEN
import com.example.simplemvvm.core_feature.util.Constants.Companion.SPLASH_SCREEN

sealed class Screen(val route: String) {
    object Splash: Screen(SPLASH_SCREEN)

    object Login: Screen(LOGIN_SCREEN)
    object Register: Screen(REGISTER_SCREEN)

    object BottomBar: Screen("bottom_bar")
    object AppScaffold: Screen("app_scaffold")
}