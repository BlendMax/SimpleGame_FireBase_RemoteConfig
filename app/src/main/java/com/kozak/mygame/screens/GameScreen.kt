package com.kozak.mygame.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kozak.mygame.R
import com.kozak.mygame.game_model.GameModel
import com.kozak.mygame.ui.theme.Gray


@Composable
fun GameScreen(gameModel: GameModel = viewModel()) {
    val enemyHitPoints by gameModel.enemyHitPoints.observeAsState()
    val timeLeftString by gameModel.timeLeftString.observeAsState()
    val progressIndicator by gameModel.progressIndicator.observeAsState()
    val gameOver by gameModel.isGameOver.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Timer display --------------------------------------------------------------------------->
        Card(
            modifier = Modifier.padding(5.dp), shape = RoundedCornerShape(15.dp), elevation = 15.dp
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.1f)
            ) {
                progressIndicator?.let {
                    LinearProgressIndicator(
                        progress = it, modifier = Modifier
                            .fillMaxHeight(0.3f)
                            .fillMaxWidth(0.9f)
                    )
                }
                Text(
                    text = timeLeftString.toString(),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

        }
        // Playing field --------------------------------------------------------------------------->
        Card(
            modifier = Modifier
                .fillMaxHeight(0.7f)
                .fillMaxWidth()
                .padding(5.dp)
                .clickable {
                    gameModel.onTap()
                }, shape = RoundedCornerShape(15.dp), elevation = 10.dp
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    // HP display field ------------------------------------------------------------>
                    Box(
                        contentAlignment = Alignment.TopStart, modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.icon_heart),
                            contentDescription = "Heart",
                            modifier = Modifier.size(75.dp)
                        )
                        Text(
                            text = enemyHitPoints.toString(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 40.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(start = 14.dp)
                        )
                    }
                    // Enemy field ----------------------------------------------------------------->
                    Box(
                        contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.icon_enemy),
                            contentDescription = "Heart",
                            modifier = Modifier.size(275.dp)
                        )
                    }
                }
            }
        }

        // Game start button ----------------------------------------------------------------------->
        Box(
            modifier = Modifier, contentAlignment = Alignment.Center
        ) {
            Button(modifier = Modifier
                .size(width = 300.dp, height = 110.dp)
                .padding(top = 40.dp),
                elevation = ButtonDefaults.elevation(15.dp),
                border = BorderStroke(1.dp, color = Gray),
                shape = RoundedCornerShape(35.dp),
                onClick = {
                    gameModel.startGame()
                }) {
                Text(
                    text = "START GAME", color = Color.White, fontWeight = FontWeight.Bold
                )
            }
        }
        // Show result of games -------------------------------------------------------------------->
        if (gameOver == true) ScoreDisplayPopup()
    }
}
