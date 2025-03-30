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
import ru.urfu.droidpractice1.SecondActivity.Companion.KEY_READ

class MainActivity : ComponentActivity(), MainScreenHandler {
    private var read: Boolean by mutableStateOf(false)
    private var likesCount: Int by mutableIntStateOf(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "inside onCreate")
        setContent {
            MainActivityScreen(
                this,
                read,
                likesCount,
                { onClickActivity() }
            )
        }
    }

    override fun onToShareClicked() {
        val share = Intent(Intent.ACTION_SEND)
        share.setType("text/plain")
        share.putExtra(Intent.EXTRA_TEXT, "Обзор мультфильма «Унесенные призраками»")
        startActivity(Intent.createChooser(share, "Поделиться"))
    }

    override fun onToOtherScreenClicked() {
        val intent = Intent(this, SecondActivity::class.java)
        resultLauncher.launch(intent)
    }

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            read = data?.getBooleanExtra(KEY_READ, false) ?: false
        }
    }

    override fun onLikeClicked() {
        likesCount++
    }

    override fun onDislikeClicked() {
        if (likesCount > 0) likesCount-- else likesCount
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
        outState.putBoolean(IS_READ, read)
        outState.putInt(LIKES_COUNT, likesCount)
        Log.d("MainActivity", "inside onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        read = savedInstanceState.getBoolean(IS_READ)
        likesCount = savedInstanceState.getInt(LIKES_COUNT)
        Log.d("MainActivity", "inside onRestoreInstanceState")
    }

    private fun onClickActivity() {
        val intent = Intent(this, SecondActivity::class.java)
            .apply {
                putExtra("read", read)
            }
        resultLauncher.launch(intent)
    }

    companion object {
        const val IS_READ = "IS_READ"
        const val LIKES_COUNT = "LIKES_COUNT"
    }

}