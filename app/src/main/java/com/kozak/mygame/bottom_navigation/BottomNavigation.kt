package com.kozak.mygame.bottom_navigation

import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

//Bottom game menu
@Composable
fun BottomNavigation(navController: NavController) {
    val listItems = listOf(
        BottomItem.GameScreen,
        BottomItem.ScoreScreen
    )

    //Uses the same names
    androidx.compose.material.BottomNavigation(
        backgroundColor = Color.White
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
        listItems.forEach {
            BottomNavigationItem(
                selected = currentRoute == it.route,
                onClick = { navController.navigate(it.route) },
                icon = {
                    Icon(
                        painter = painterResource(id = it.iconId),
                        contentDescription = "Icon"
                    )
                },
                selectedContentColor = Color.Blue,
                unselectedContentColor = Color.Gray
            )
        }
    }
}