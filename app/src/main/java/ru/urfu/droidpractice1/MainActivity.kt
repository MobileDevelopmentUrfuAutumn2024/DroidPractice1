package ru.urfu.droidpractice1

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import ru.urfu.droidpractice1.content.MainActivityScreen


class MainActivity : ComponentActivity() {
    private val PREFS_NAME = "switchStatus"
    private val PREFS_KEY_SWITCH_READ = "switch_read"
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")


        val sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val isRead = sharedPreferences.getBoolean(PREFS_KEY_SWITCH_READ, false)

        setContent {
            MainActivityScreen(isRead)
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1) {
            val sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val newIsReadValue = sharedPreferences.getBoolean(PREFS_KEY_SWITCH_READ, false)
            sharedPreferences.edit().putBoolean(PREFS_KEY_SWITCH_READ, newIsReadValue).apply()


            setContent {
                MainActivityScreen(isRead = newIsReadValue)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }









}

