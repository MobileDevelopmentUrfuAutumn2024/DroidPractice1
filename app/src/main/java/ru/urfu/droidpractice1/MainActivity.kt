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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(LIFE_CYCLE_TAG, "Callback \"onCreate\" was called from \"MainActivity\"")

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

    private fun onOtherArticleCardClicked() {
        val intent = Intent(this, SecondActivity::class.java).apply {
            putExtra(KEY_READ, isSecondArticleRead)
        }
        resultLauncher.launch(intent)
    }

    private fun onShareClicked() {
        Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, getString(R.string.article1_title))
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

        Log.d(LIFE_CYCLE_TAG, "Callback \"onSaveInstanceState\" was called from \"MainActivity\"")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        savedInstanceState.apply {
            isSecondArticleRead = getBoolean(KEY_READ, isSecondArticleRead)
            likesCount = getInt(KEY_LIKES_VALUE, likesCount)
            dislikesCount = getInt(KEY_DISLIKES_VALUE, dislikesCount)
        }

        Log.d(LIFE_CYCLE_TAG, "Callback \"onRestoreInstanceState\" was called from \"MainActivity\"")
    }


    override fun onStart() {
        super.onStart()
        Log.d(LIFE_CYCLE_TAG, "Callback \"onStart\" was called from \"MainActivity\"")
    }

    override fun onResume() {
        super.onResume()
        Log.d(LIFE_CYCLE_TAG, "Callback \"onResume\" was called from \"MainActivity\"")
    }

    override fun onPause() {
        super.onPause()
        Log.d(LIFE_CYCLE_TAG, "Callback \"onPause\" was called from \"MainActivity\"")
    }

    override fun onStop() {
        super.onStop()
        Log.d(LIFE_CYCLE_TAG, "Callback \"onStop\" was called from \"MainActivity\"")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(LIFE_CYCLE_TAG, "Callback \"onRestart\" was called from \"MainActivity\"")
    }

    override fun onDestroy() {
        Log.d(LIFE_CYCLE_TAG, "Callback \"onDestroy\" was called from \"MainActivity\"")
        super.onDestroy()
    }

    companion object {
        const val LIFE_CYCLE_TAG = "LifeCycleTag"
        const val KEY_LIKES_VALUE = "LIKES_VALUE"
        const val KEY_DISLIKES_VALUE = "DISLIKES_VALUE"
    }
}