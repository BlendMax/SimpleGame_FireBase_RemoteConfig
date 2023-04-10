package com.kozak.mygame.screens

import android.graphics.Bitmap
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kozak.mygame.firebase.FbModel

@Composable
fun WebViewScreen(fbModel: FbModel = viewModel()) {
    val link by fbModel.link.observeAsState()
    var backEnabled by remember { mutableStateOf(false) }
    var webView: WebView? = null

    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = object : WebViewClient(){
                override fun onPageStarted(view: WebView, url: String?, favicon: Bitmap?) {
                    backEnabled = view.canGoBack()
                }
            }
            webView = this
            link?.let { it1 -> loadUrl(it1) }
        }
    }, update = {
        webView = it
    })
    // Return to the main page
    BackHandler(enabled = backEnabled) {
        webView?.goBack()
    }
}