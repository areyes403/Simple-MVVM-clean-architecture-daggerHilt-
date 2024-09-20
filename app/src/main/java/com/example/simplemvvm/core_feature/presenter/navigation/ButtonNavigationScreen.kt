package com.example.simplemvvm.core_feature.presenter.navigation

import androidx.annotation.DrawableRes
import com.example.simplemvvm.R
import com.example.simplemvvm.core_feature.util.Constants.Companion.HOME_SCREEN
import com.example.simplemvvm.core_feature.util.Constants.Companion.PROFILE_SCREEN
import com.example.simplemvvm.core_feature.util.Constants.Companion.SETTINGS_SCREEN

sealed class ButtonNavigationScreen(val route: String, val title: String, @DrawableRes val icon: Int) {
    object Home : ButtonNavigationScreen(
        route =HOME_SCREEN,
        title = "Home",
        icon = R.drawable.ic_home
    )

    object Profile : ButtonNavigationScreen(
        route = PROFILE_SCREEN,
        title = "Profile",
        icon = R.drawable.ic_chat
    )

    object Settings : ButtonNavigationScreen(
        route = SETTINGS_SCREEN,
        title = "Settings",
        icon = R.drawable.ic_settings
    )
}