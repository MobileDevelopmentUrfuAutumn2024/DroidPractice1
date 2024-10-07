package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {

    private var isRead: Boolean by mutableStateOf(false)


    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                isRead = result.data?.getBooleanExtra(KEY_READ, false) ?: false
            }
        }


    fun onSecondActivityClick() {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(KEY_READ, isRead)
        resultLauncher.launch(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityScreen(this, isRead)
        }
    }

    companion object {
        const val KEY_READ = "READ"
    }

    override fun onStart() {
        Log.d("SecondActivity", "inside onStart")
        super.onStart()
    }

    override fun onRestart() {
        Log.d("SecondActivity", "inside onRestart")
        super.onRestart()
    }

    override fun onResume() {
        Log.d("SecondActivity", "inside onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("SecondActivity", "inside onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("SecondActivity", "inside onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("SecondActivity", "inside onDestroy")
        super.onDestroy()
    }
}