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
import ru.urfu.droidpractice1.SecondActivity.Companion.READ_KEY
import ru.urfu.droidpractice1.content.MainActivityScreen
import ru.urfu.droidpractice1.data.Article
import ru.urfu.droidpractice1.data.Stats

/**
 * Основная активность
 */
class MainActivity : ComponentActivity(), MainScreenHandler {

    private var readed by mutableStateOf(false)

    private var likes by mutableIntStateOf(0)
    private var dislikes by mutableIntStateOf(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityScreen(this, read = readed, stats = Stats(likes, dislikes))
        }
        Log.e(LIFECYCLE_KEY, "MainActivity onCreate")
    }

    override fun onToArticleClick() {
        val intent = Intent(this, SecondActivity::class.java)
            .apply {
                putExtra(READ_KEY, readed)
            }
        resultLauncher.launch(intent)
    }

    override fun onToShareClick() {
        val share = Intent(Intent.ACTION_SEND)
        share.type = "text/plain"
        share.putExtra(Intent.EXTRA_TEXT, Article.articles[0].name)
        startActivity(Intent.createChooser(share, R.string.send_article.toString()))
    }

    override fun onClickLike() {
        likes += 1
    }

    override fun onClickDislike() {
        dislikes += 1
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(LIKES_KEY, likes)
        outState.putInt(DISLIKES_KEY, dislikes)
        Log.e(LIFECYCLE_KEY, "MainActivity onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        likes = savedInstanceState.getInt(LIKES_KEY)
        dislikes = savedInstanceState.getInt(DISLIKES_KEY)
        Log.e(LIFECYCLE_KEY, "MainActivity onRestoreInstanceState")
    }

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            readed = data?.getBooleanExtra(READ_KEY, false) ?: false
        }
    }

    override fun onStart() {
        super.onStart()
        Log.e(LIFECYCLE_KEY, "MainActivity onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.e(LIFECYCLE_KEY, "MainActivity onStop")
    }

    override fun onResume() {
        super.onResume()
        Log.e(LIFECYCLE_KEY, "MainActivity onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e(LIFECYCLE_KEY, "MainActivity onRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.e(LIFECYCLE_KEY, "MainActivity onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(LIFECYCLE_KEY, "MainActivity onDestroy")
    }

    companion object {
        const val LIKES_KEY = "LIKES"
        const val DISLIKES_KEY = "DISLIKES"
        const val LIFECYCLE_KEY = "LIFECYCLE"
    }
}