package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {
    private var countLike: Int by mutableIntStateOf(0)
    private var countDislike: Int by mutableIntStateOf(0)
    private var isRead: Boolean by mutableStateOf(false)
    private lateinit var secondActivityLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityScreen(
                ::like,
                ::dislike,
                countLike,
                countDislike,
                isRead,
                ::clickActivity,
                ::shareArticle
            )
        }
    }

    override fun onStart() {
        Log.d("MainActivity", "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("MainActivity", "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("MainActivity", "onPause")
        super.onPause()
    }

    override fun onRestart() {
        Log.d("MainActivity", "onRestart")
        super.onRestart()
    }

    override fun onStop() {
        Log.d("MainActivity", "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("MainActivity", "onDestroy")
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("likesCount", countLike)
        outState.putInt("dislikesCount", countDislike)
        outState.putBoolean("read", isRead)
        Log.d("MainActivity", "onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        countLike = savedInstanceState.getInt("likesCount")
        countDislike = savedInstanceState.getInt("dislikesCount")
        isRead = savedInstanceState.getBoolean("read")
        Log.d("MainActivity", "onRestoreInstanceState")
    }

    private fun like() {
        countLike++
    }

    private fun dislike() {
        countDislike++
    }

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
            result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            isRead = data?.getBooleanExtra("read", false) ?: false
        }
    }

    private fun clickActivity() {
        val intent = Intent(this, SecondActivity::class.java)
            .apply {
                putExtra("read", isRead)
            }
        resultLauncher.launch(intent)
    }

    private fun shareArticle() {
        val share = Intent(Intent.ACTION_SEND)
        share.setType("text/plain")
        startActivity((Intent.createChooser(share, "Поделиться")))
    }
}