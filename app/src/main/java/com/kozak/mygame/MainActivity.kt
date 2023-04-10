package com.kozak.mygame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import com.kozak.mygame.screens.ScreenSelector

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback(this, onBackInvokedCallback)
        setContent {
            ScreenSelector()
        }
    }
    // Does not allow you to exit the application using the system back button
    private val onBackInvokedCallback = object : OnBackPressedCallback(true){
        override fun handleOnBackPressed() {
            if (isTaskRoot) {
                return
            }
            finish()
        }
    }
}
