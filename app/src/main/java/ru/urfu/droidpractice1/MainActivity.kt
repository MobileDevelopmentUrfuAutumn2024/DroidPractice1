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
import ru.urfu.droidpractice1.interfaces.MainScreenHandler

class MainActivity : ComponentActivity(), MainScreenHandler {
    private var isRead: Boolean by mutableStateOf(false)

    private var likes: Int by mutableIntStateOf(0)
    private var dislikes: Int by mutableIntStateOf(0)

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                isRead = data?.getBooleanExtra(KEY_READ, false) ?: false
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityScreen(this, isRead, likes, dislikes)
        }
    }

    override fun onToShare() {
        val share = Intent(Intent.ACTION_SEND)
        share.setType("text/plain")
        share.putExtra(
            Intent.EXTRA_TEXT,
            "Поднимаем свой полноценный игровой Minecraft сервер с мини-играми"
        )
        startActivity(Intent.createChooser(share, "Поделиться"))
    }

    override fun onLike() {
        likes++;
    }

    override fun onDislike() {
        dislikes++;
    }

    override fun onToOtherScreen() {
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
        outState.putBoolean(READ, isRead)
        outState.putInt(LIKE, likes)
        outState.putInt(DISLIKE, dislikes)
        Log.d("MainActivity", "onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        isRead = savedInstanceState.getBoolean(READ)
        likes = savedInstanceState.getInt(LIKE)
        dislikes = savedInstanceState.getInt(DISLIKE)
        Log.d("MainActivity", "onRestoreInstanceState")
    }

    companion object {
        const val READ = "READ"
        const val LIKE = "LIKE_COUNT"
        const val DISLIKE = "DISLIKE_COUNT"
    }
}