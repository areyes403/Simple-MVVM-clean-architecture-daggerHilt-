package com.example.simplemvvm.core_feature.presenter.navigation

import com.example.simplemvvm.core_feature.util.Constants.Companion.AUTHENTICADED
import com.example.simplemvvm.core_feature.util.Constants.Companion.LOGIN_SCREEN
import com.example.simplemvvm.core_feature.util.Constants.Companion.REGISTER_SCREEN
import com.example.simplemvvm.core_feature.util.Constants.Companion.UNAUTHENTICADED

sealed class NavigationRoutes{
    sealed class Unauthenticated(val route: String) : NavigationRoutes() {
        object NavigationRoute : Unauthenticated(route = UNAUTHENTICADED)
        object Login : Unauthenticated(route = LOGIN_SCREEN)
        object Registration : Unauthenticated(route = REGISTER_SCREEN)
    }

    sealed class Authenticated(val route: String):NavigationRoutes(){
        object NavigationRoute:Authenticated(route = AUTHENTICADED)
    }

}
