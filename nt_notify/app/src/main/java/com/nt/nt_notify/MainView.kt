package com.nt.nt_notify

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DrawerItem(selected: Boolean, item: Screen, onDrawerItemClicked: () -> Unit) {
    val background = if (selected) Color.LightGray else Color.Transparent
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp, vertical = 16.dp)
        .background(background, shape = RoundedCornerShape(8.dp))
        .clickable { onDrawerItemClicked() }
    ) {
        Row(modifier = Modifier.padding(horizontal = 10.dp)) {
            Icon(
                painter = painterResource(id = item.icon),
                contentDescription = item.title,
                Modifier.padding(end = 8.dp, top = 4.dp)
            )
            Text(
                text = item.title,
                style = MaterialTheme.typography.headlineLarge,
                fontSize = 24.sp
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainView() {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val scope: CoroutineScope = rememberCoroutineScope()
    val mainViewModel: MainViewModel = viewModel()

    // The follow declarations are for the ModalBottomSheetLayout and the MoreBottomSheet.
    val isSheetFullScreen by remember { mutableStateOf(false) }
    val bottomSheetModifier = if(isSheetFullScreen) Modifier.fillMaxSize() else Modifier.fillMaxWidth()
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded }
    )
    val radius = if(isSheetFullScreen) 0.dp else 12.dp

    // Allow us to find the view we are currently in.
    val navController: NavController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val currentScreen = remember{ mainViewModel.currentScreen.value }
    val dialogOpen = remember{ mutableStateOf(false) }
    val title = remember{ mutableStateOf(currentScreen.title) }

    // Okay, lets start making Kotlin look like js.
    val topBar: @Composable () -> Unit = {
        TopAppBar(
            title = { Text(text = mainViewModel.currentScreen.value.title) },
            actions = {
                IconButton(onClick = { scope.launch {
                    if(sheetState.isVisible) sheetState.hide() else sheetState.show()
                } }) {
                    Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Top Bar Actions")
                }
            },
            backgroundColor = Color.LightGray,
            navigationIcon = { IconButton(onClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(painter = painterResource(id = mainViewModel.currentScreen.value.icon), contentDescription = "Account")
            }
            })
    }

    val navigateBottomBar: (Screen) -> Unit = { screen ->
        mainViewModel.setCurrentScreen(screen)
        navController.navigate(screen.route)
        // Navigation is happen in two different places in the app. (Check DrawerItem and bottomBar)
        // Which is fine because the changes are synchronized in the view model.
    }

    val bottomBar: @Composable () -> Unit = {
        if(currentScreen.type == ScreenType.DRAWER || currentScreen == Screen.Home) {
            BottomNavigation(modifier = Modifier.wrapContentSize(), backgroundColor = Color.LightGray) {
                bottomScreens.forEach { item ->
                    val isSelected = currentRoute == item.route
                    BottomNavigationItem(selected = isSelected,
                        onClick = { navigateBottomBar(item) }, icon = {
                            val tint = if(isSelected) Color.Green else Color.Black
                            Icon(painter = painterResource(id = item.icon), contentDescription = item.title, tint = tint)
                        },
                        label = {
                            val fontWeight = if(isSelected) FontWeight.ExtraBold else FontWeight.SemiBold
                            Text(text = item.title, fontWeight = fontWeight)},
                        selectedContentColor = Color.Transparent,
                        unselectedContentColor = Color.Black,
                    )
                }
            }
        }
    }

    Scaffold(
        bottomBar = bottomBar,
        topBar = topBar,
        scaffoldState = scaffoldState,
        drawerContent = {
            LazyColumn(modifier = Modifier.padding(16.dp)) {
                items(drawerScreens) { item ->
                    DrawerItem(selected = currentRoute == item.route, item = item) {
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                        if(item.route == Screen.Login.route) {
                            dialogOpen.value = true
                        } else {
                            navigateBottomBar(item)
                            title.value = item.title
                        }
                    }
                }
            }
        }
    ) {
        Navigation(navController = navController, viewModel = mainViewModel, pd = it)
        LoginAccountDialogue(dialogOpen = dialogOpen, mainViewModel = mainViewModel)
    }
}