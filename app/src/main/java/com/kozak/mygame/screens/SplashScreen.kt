package com.kozak.mygame.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.kozak.mygame.R
import com.kozak.mygame.constants.RoutesConst
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController) {
    showBars(flag = false)
    LaunchedEffect(key1 = true) {
        delay(2000)
        navController.navigate(RoutesConst.GAME_SCREEN) {
            popUpTo(0)
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.splash),
            contentDescription = "splash_screen"
        )

    }

}