package com.kozak.mygame.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kozak.mygame.firebase.FbModel

// Selects the desired screen depending on the settings
@Composable
fun ScreenSelector(fbModel: FbModel = viewModel()) {
    val status by fbModel.status.observeAsState()

    if (status == true) WebViewScreen() else MainScreen()
}