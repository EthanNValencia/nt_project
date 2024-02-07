package com.nt.nt_notify

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

data class LibraryCategory(val name: String, @DrawableRes val icon: Int)

data class BottomSheetOption(val name: String, @DrawableRes val icon: Int)

enum class ScreenType {
    DRAWER,
    BOTTOM
}

sealed class Screen(val title: String, val route: String, @DrawableRes val icon: Int, val type: ScreenType) {
    data object Account: Screen(title = "Account", route = "account", icon = R.drawable.ic_account, type = ScreenType.DRAWER)
    data object Subscription: Screen(title = "Subscription", route = "subscribe", icon =  R.drawable.ic_subscribe, type = ScreenType.DRAWER)
    data object Login: Screen(title = "Login", route = "login", icon = R.drawable.baseline_login_24, type = ScreenType.DRAWER)
    data object Home: Screen(title = "Home", route = "home", icon = R.drawable.baseline_music_video_24, type = ScreenType.BOTTOM)
    data object Library: Screen(title = "Library", route = "library", icon = R.drawable.baseline_video_library_24, type = ScreenType.BOTTOM)
    data object Browse: Screen(title = "Browse", route = "browse", icon = R.drawable.baseline_apps_24, type = ScreenType.BOTTOM)
}

val drawerScreens = listOf(Screen.Account, Screen.Subscription, Screen.Login)
val bottomScreens = listOf(Screen.Home, Screen.Library, Screen.Browse)

@Composable
fun Navigation(navController: NavController, viewModel: MainViewModel, pd: PaddingValues) {
    NavHost(navController = navController as NavHostController, startDestination = viewModel.currentScreen.value.route, modifier = Modifier.padding(pd)) {
        // With add account I want a pop up to open, that is why it is not here.
        composable(route = Screen.Account.route) {
            // AccountView()
            Text(text = "AccountView")
        }
        composable(route = Screen.Subscription.route) {
            // SubscriptionView()
            Text(text = "SubscriptionView")
        }
        composable(route = Screen.Home.route) {
            // HomeView(viewModel)
            Text(text = "HomeView")
        }
        composable(route = Screen.Browse.route) {
            // BrowseView(viewModel)
            Text(text = "BrowseView")
        }
        composable(route = Screen.Library.route) {
            // LibraryView(viewModel = viewModel)
            Text(text = "LibraryView")
        }
    }

}
