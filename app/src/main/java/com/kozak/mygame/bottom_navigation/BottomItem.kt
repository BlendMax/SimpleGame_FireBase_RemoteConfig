package com.kozak.mygame.bottom_navigation

import com.kozak.mygame.R
import com.kozak.mygame.constants.RoutesConst

sealed class BottomItem(val iconId: Int, val route: String) {
    object GameScreen: BottomItem(R.drawable.icon_game, RoutesConst.GAME_SCREEN)
    object ScoreScreen: BottomItem(R.drawable.icon_score, RoutesConst.SCORE_SCREEN)
}
