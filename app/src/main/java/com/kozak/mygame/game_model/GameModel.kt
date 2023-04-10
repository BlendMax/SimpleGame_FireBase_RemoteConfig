package com.kozak.mygame.game_model

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.CountDownTimer
import androidx.core.content.edit
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kozak.mygame.constants.GameConst

class GameModel(application: Application) : AndroidViewModel(application) {
    private lateinit var enemy: Enemy
    private lateinit var score: Score
    private lateinit var timer: CountDownTimer
    private var timeLeft: Long = GameConst.DEFAULT_COUNTDOWN_TIME // 4 seconds
    private var timeCountdownInterval: Long = GameConst.DEFAULT_COUNTDOWN_INTERVAL // 1 seconds
    private var isGameRunning = false // Game status
    private var winsCount = 0 // Number of wins in a row
    private val pref: SharedPreferences =
        application.getSharedPreferences("game_score", Context.MODE_PRIVATE)

    private val _enemyHitPoints = MutableLiveData<Int>()
    val enemyHitPoints: LiveData<Int>
        get() = _enemyHitPoints

    private val _isGameOver = MutableLiveData<Boolean>()
    val isGameOver: LiveData<Boolean>
        get() = _isGameOver

    private val _timeLeftString = MutableLiveData<String>("3")
    val timeLeftString: LiveData<String>
        get() = _timeLeftString

    private var _progressIndicator = MutableLiveData<Float>(1f)
    val progressIndicator: LiveData<Float>
        get() = _progressIndicator

    private var _gameScore = MutableLiveData<Int>(0)
    val gameScore: LiveData<Int>
        get() = _gameScore

    private var _highGameScore = MutableLiveData<Int>(0)
    val highGameScore: LiveData<Int>
        get() = _highGameScore

    init {
        resetGame()
    }

    // Make a hit
    fun onTap() {
        if (isGameRunning) {
            enemy.hitPoints--
            score.score++
            _enemyHitPoints.value = enemy.hitPoints
            if (enemy.hitPoints == 0) {
                // Player wins
                timer.cancel()
                playerWin()
            }
        }
    }

    fun startGame() {
        isGameRunning = true
        timer.start()
    }

    private fun resetGame() {
        enemy = Enemy()
        score = Score()
        _enemyHitPoints.value = enemy.hitPoints
        _highGameScore.value = pref.getInt("score", 0)
        _isGameOver.value = false

        timer = object : CountDownTimer(timeLeft, timeCountdownInterval) {

            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished
                val secondsLeft = (millisUntilFinished / timeCountdownInterval).toString()
                _timeLeftString.postValue(secondsLeft)
                val progressReducer = _progressIndicator.value?.minus(0.25f)
                _progressIndicator.postValue(progressReducer)
            }

            override fun onFinish() {
                isGameRunning = false
                _isGameOver.postValue(true)
                enemy = Enemy()
                _enemyHitPoints.value = enemy.hitPoints
                _progressIndicator.postValue(1f)
                _timeLeftString.postValue(GameConst.DEFAULT_COUNTDOWN_TIME_TEXT)
                winsCount = 0
                writeScore(score.score)
                _gameScore.value = score.score
            }
        }
    }

    // Record/update new best score
    private fun writeScore(score: Int) {
        val previousScore = pref.getInt("score", 0)
        if (score > previousScore) {
            pref.edit(commit = false) {
                putInt("score", score)
                _highGameScore.value = score
            }
        }
    }


    private fun playerWin() {
        _progressIndicator.postValue(1f)
        enemy = Enemy()
        winsCount += 1
        enemy.hitPoints += 2 * winsCount // Increase enemy hitpoints by 2 for each victory
        _enemyHitPoints.value = enemy.hitPoints
        timer.start()
    }

    fun setGameOverStatus(status: Boolean) {
        _isGameOver.value = status
    }
}