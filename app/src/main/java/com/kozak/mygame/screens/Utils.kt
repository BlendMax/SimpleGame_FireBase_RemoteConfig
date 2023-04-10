package com.kozak.mygame.screens

import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

// Removes unnecessary screen components
@Composable
fun showBars(flag: Boolean) {
    rememberSystemUiController().apply {
        this.isSystemBarsVisible = flag
        this.isNavigationBarVisible = flag
        this.isStatusBarVisible = flag
    }


}