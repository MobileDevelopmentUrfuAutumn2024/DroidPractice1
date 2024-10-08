package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.urfu.droidpractice1.SecondActivity.Companion.KEY_READ
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity(), MainScreenHandler {
    private var read: Boolean by mutableStateOf(false)

    private var countLike: Int by mutableIntStateOf(0)
    private var countDislike: Int by mutableIntStateOf(0)

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                read = data?.getBooleanExtra(KEY_READ, false) ?: false
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityScreen(this, read, countLike, countDislike)
        }
    }

    override fun onToShareClicked() {
        val share = Intent(Intent.ACTION_SEND)
        share.setType("text/plain")
        share.putExtra(
            Intent.EXTRA_TEXT,
            "Разработчик утилиты Rufus попросил пользователей принять проблему"
        )
        startActivity(Intent.createChooser(share, "Переслать статью"))
    }

    override fun onLikeClicked() {
        countLike++;
    }

    override fun onDislikeClicked() {
        countDislike++;
    }

    override fun onToOtherScreenClicked() {
        val intent = Intent(this, SecondActivity::class.java)
        resultLauncher.launch(intent)
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
        outState.putInt(LIKE_KEY, countLike)
        outState.putInt(DISLIKE_KEY, countDislike)
        outState.putBoolean(READ_KEY, read)
        Log.d("MainActivity", "onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        countLike = savedInstanceState.getInt(LIKE_KEY)
        countDislike = savedInstanceState.getInt(DISLIKE_KEY)
        read = savedInstanceState.getBoolean(READ_KEY)
        Log.d("MainActivity", "onRestoreInstanceState")
    }

    companion object {
        const val READ_KEY = "READ"
        const val LIKE_KEY = "LIKE_COUNT"
        const val DISLIKE_KEY = "DISLIKE_COUNT"
    }
}