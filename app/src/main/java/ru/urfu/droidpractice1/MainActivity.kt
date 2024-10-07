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

class MainActivity : ComponentActivity(), MainScreenController {

    private var hasRead: Boolean by mutableStateOf(false)
    private var likesCount: Int by mutableIntStateOf(0)

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                hasRead = result.data?.getBooleanExtra(SecondActivity.KEY_READ, false) ?: false
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "inside onCreate")
        setContent {
            MainActivityScreen(this, likesCount, hasRead)
        }
    }

    override fun onShareClick() {
        Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, getString(R.string.article_header))
            startActivity(Intent.createChooser(this, "Поделиться"))
        }
    }

    override fun onLikeClick() {
        likesCount++
    }

    override fun onDislikeClick() {
        if (likesCount > 0) likesCount-- else likesCount
    }

    override fun onStart() {
        Log.d("MainActivity", "onStart")
        super.onStart()
    }

    override fun onRestart() {
        Log.d("MainActivity", "onRestart")
        super.onRestart()
    }

    override fun onResume() {
        Log.d("MainActivity", "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("MainActivity", "onPause")
        super.onPause()
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
        outState.putBoolean(HAS_READ, hasRead)
        outState.putInt(LIKES_COUNT, likesCount)
        Log.d("MainActivity", "onSaveInstanceState")
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        hasRead = savedInstanceState.getBoolean(HAS_READ)
        likesCount = savedInstanceState.getInt(LIKES_COUNT)
        Log.d("MainActivity", "onRestoreInstanceState")
    }

    override fun onOtherScreenClick() {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(HAS_READ, hasRead)
        resultLauncher.launch(intent)
    }

    companion object {
        const val HAS_READ = "HAS_READ"
        const val LIKES_COUNT = "LIKES_COUNT"
    }
}