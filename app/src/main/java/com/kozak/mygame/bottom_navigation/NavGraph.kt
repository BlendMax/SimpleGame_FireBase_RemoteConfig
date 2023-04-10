package com.kozak.mygame.bottom_navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kozak.mygame.constants.RoutesConst
import com.kozak.mygame.screens.GameScreen
import com.kozak.mygame.screens.WebViewScreen
import com.kozak.mygame.screens.GameScoreScreen
import com.kozak.mygame.screens.SplashScreen

@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = RoutesConst.SPLASH_SCREEN){
        composable(RoutesConst.GAME_SCREEN){
            GameScreen()
        }
        composable(RoutesConst.WEB_VIEW_SCREEN){
            WebViewScreen()
        }
        composable(RoutesConst.SCORE_SCREEN){
            GameScoreScreen()
        }
        composable(RoutesConst.SPLASH_SCREEN){
            SplashScreen(navController = navHostController)
        }
    }

}