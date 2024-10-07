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

    private var isRead: Boolean by mutableStateOf(false)
    private var likesCount: Int by mutableIntStateOf(0)

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                isRead = result.data?.getBooleanExtra(KEY_READ, false) ?: false
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityScreen(
                this,
                isRead,
                likesCount
            )
        }
    }

    override fun onToShareClicked() {
        Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, getString(R.string.main_article_header))
            startActivity(Intent.createChooser(this, "Поделиться"))
        }
    }

    override fun onThumbUpClicked() {
        likesCount++
    }

    override fun onThumbDownClicked() {
        if (likesCount > 0) likesCount-- else likesCount
    }

    override fun onToOtherScreenClicked() {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(IS_READ, isRead)
        resultLauncher.launch(intent)
    }

    override fun onStart() {
        Log.d("MainActivity", "inside onStart")
        super.onStart()
    }

    override fun onRestart() {
        Log.d("MainActivity", "inside onRestart")
        super.onRestart()
    }

    override fun onResume() {
        Log.d("MainActivity", "inside onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("MainActivity", "inside onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("MainActivity", "inside onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("MainActivity", "inside onDestroy")
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(IS_READ, isRead)
        outState.putInt(LIKES_COUNT, likesCount)
        Log.d("MainActivity", "inside onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        isRead = savedInstanceState.getBoolean(IS_READ)
        likesCount = savedInstanceState.getInt(LIKES_COUNT)
        Log.d("MainActivity", "inside onRestoreInstanceState")
    }

    companion object {
        const val IS_READ = "IS_READ"
        const val LIKES_COUNT = "LIKES_COUNT"
    }
}