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

class MainActivity : ComponentActivity() {
    private var isSecondArticleRead: Boolean by mutableStateOf(false)
    private var likesCount: Int by mutableIntStateOf(0)
    private var dislikesCount: Int by mutableIntStateOf(0)

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                isSecondArticleRead = data?.getBooleanExtra(KEY_READ, false) ?: false
            }
        }

    private fun onOtherArticleCardClicked() {
        val intent = Intent(this, SecondActivity::class.java).apply {
            putExtra(KEY_READ, isSecondArticleRead)
        }
        resultLauncher.launch(intent)
    }

    private fun onShareClicked() {
        Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, getString(R.string.main_title))
            startActivity(Intent.createChooser(this, "Поделиться"))
        }
    }

    private fun onLikeClicked() {
        likesCount++
    }

    private fun onDislikeClicked() {
        dislikesCount++
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.apply {
            putBoolean(KEY_READ, isSecondArticleRead)
            putInt(KEY_LIKES_VALUE, likesCount)
            putInt(KEY_DISLIKES_VALUE, dislikesCount)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.apply {
            isSecondArticleRead = getBoolean(KEY_READ, isSecondArticleRead)
            likesCount = getInt(KEY_LIKES_VALUE, likesCount)
            dislikesCount = getInt(KEY_DISLIKES_VALUE, dislikesCount)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("cycleMethods", "Main activity called \"onCreate\"")
        setContent {
            MainActivityScreen(
                ::onOtherArticleCardClicked,
                ::onShareClicked,
                ::onLikeClicked,
                ::onDislikeClicked,
                isSecondArticleRead,
                likesCount,
                dislikesCount
            )
        }
    }

    override fun onStart() {
        Log.d("cycleMethods", "Main activity called \"onStart\"")
        super.onStart()
    }
    override fun onResume() {
        Log.d("cycleMethods", "Main activity called \"onResume\"")
        super.onResume()
    }
    override fun onPause() {
        Log.d("cycleMethods", "Main activity called \"onPause\"")
        super.onPause()
    }
    override fun onStop() {
        Log.d("cycleMethods", "Main activity called \"onStop\"")
        super.onStop()
    }
    override fun onRestart() {
        Log.d("cycleMethods", "Main activity called \"onRestart\"")
        super.onRestart()
    }
    override fun onDestroy() {
        Log.d("cycleMethods", "Main activity called \"onDestroy\"")
        super.onDestroy()
    }

    companion object {
        const val KEY_LIKES_VALUE = "LIKES_VALUE"
        const val KEY_DISLIKES_VALUE = "DISLIKES_VALUE"
    }
}