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
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity(), MainScreenHandler {

    private var hasRead: Boolean by mutableStateOf(false)
    private var likeCounter: Int by mutableIntStateOf(0)
    private var dislikeCounter: Int by mutableIntStateOf(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate")
        setContent {
            MainActivityScreen(this, hasRead, likeCounter.toString(), dislikeCounter.toString())
        }
    }

    override fun onAddLike() {
        likeCounter++
    }

    override fun onAddDislike() {
        dislikeCounter++
    }

    override fun onGoingNext() {
        val intent = Intent(this, SecondActivity::class.java)
            .apply { putExtra("READ_KEY", hasRead) }
        goingNextLauncher.launch(intent)
    }

    override fun onShare() {
        val share = Intent(Intent.ACTION_SEND)
            .apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, getString(R.string.article_title))
            }
        startActivity(Intent.createChooser(share, "Поделиться"))
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivity", "onRestart")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "onStop")
    }

    override fun onDestroy() {
        Log.d("MainActivity", "onDestroy")
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(HAS_READ_KEY, hasRead)
        outState.putInt(LIKE_KEY, likeCounter)
        outState.putInt(DISLIKE_KEY, dislikeCounter)
        Log.d("MainActivity", "onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        hasRead = savedInstanceState.getBoolean(HAS_READ_KEY)
        likeCounter = savedInstanceState.getInt(LIKE_KEY)
        dislikeCounter = savedInstanceState.getInt(DISLIKE_KEY)
        Log.d("MainActivity", "onRestoreInstanceState")
    }

    private val goingNextLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            hasRead = result.data?.getBooleanExtra(SecondActivity.HAS_READ_KEY, false) ?: false
        }
    }

    companion object {
        const val HAS_READ_KEY = "HAS_READ_KEY"
        const val LIKE_KEY = "LIKE_KEY"
        const val DISLIKE_KEY = "DISLIKE_KEY"
    }
}