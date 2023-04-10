package com.kozak.mygame.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kozak.mygame.game_model.GameModel

@Composable
fun GameScoreScreen(gameModel: GameModel = viewModel()){
    val scoreText by gameModel.highGameScore.observeAsState()

    Column(modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center) {
        Box(contentAlignment = Alignment.Center) {
            Text(text = "HIGH SCORE!",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.size(10.dp))
        Box(contentAlignment = Alignment.Center) {
            Text(text = scoreText.toString(),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold)
        }

    }

}