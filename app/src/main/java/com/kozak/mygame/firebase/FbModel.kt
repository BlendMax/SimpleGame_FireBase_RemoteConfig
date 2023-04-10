package com.kozak.mygame.firebase

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.kozak.mygame.R


class FbModel(application: Application) : AndroidViewModel(application) {
    private val remoteConfig = Firebase.remoteConfig
    private val pref: SharedPreferences = application
        .getSharedPreferences("remote_config_defaults", Context.MODE_PRIVATE)

    private var _link = MutableLiveData<String>()
    val link: LiveData<String>
        get() = _link

    private var _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean>
        get() = _status

    init {
        // Set up Firebase Remote Config
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
        remoteConfig.setConfigSettingsAsync(
            remoteConfigSettings {
                minimumFetchIntervalInSeconds = 60 // 1 minute
            }
        )
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val defaultStatus = pref.getBoolean(STATUS, false)
                val url = remoteConfig.getString(LINK)
                val status = remoteConfig.getBoolean(STATUS)
                _link.value = url

                // Replace default settings with those obtained from the firebase
                if (defaultStatus){
                    changeDefaultSettings(url, true)
                    _status.value = true
                } else {
                    changeDefaultSettings(url, status)
                    _status.value = status
                }
            } else {
                // Get data from default settings
                _link.value = pref.getString(LINK, "")
                _status.value = pref.getBoolean(STATUS, false)
            }
        }
    }

    private fun changeDefaultSettings(link: String, status: Boolean){
        pref.edit(commit = false) {
            putString(LINK, link)
            putBoolean(STATUS, status)
        }
    }
    companion object {
        const val LINK = "link"
        const val STATUS = "status"
    }
}