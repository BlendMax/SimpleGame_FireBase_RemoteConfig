package com.kozak.mygame.screens

import android.content.Context
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kozak.mygame.game_model.GameModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ScoreDisplayPopup(gameModel: GameModel = viewModel()) {
    val showDialog by gameModel.isGameOver.observeAsState()
    val gameScore by gameModel.gameScore.observeAsState()

    if (showDialog == true) {
        AlertDialog(
            onDismissRequest = { gameModel.setGameOverStatus(false) },
            title = { Text("Your score:", fontSize = 30.sp) },
            text = { Text(gameScore.toString(),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold) },
            confirmButton = { Button(onClick = { gameModel.setGameOverStatus(false) }
                ) {
                    Text("OK")
                }
            }
        )
    }
}