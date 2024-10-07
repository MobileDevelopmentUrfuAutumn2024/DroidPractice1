package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {
    private var likesCount: Int = 0
    private var secondArticleIsRead: Boolean = false
    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            secondArticleIsRead = result.data?.getBooleanExtra(SECOND_ARTICLE_IS_READ, false) ?: false
            Log.d("MainActivity", "Article is read: ${secondArticleIsRead}")
        }
    }

    // Lifecycle activities
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("MainActivity", "onCreate")
        super.onCreate(savedInstanceState)

        setContent {
            MainActivityScreen(
                likesCount = likesCount,
                secondArticleIsRead = secondArticleIsRead,
                modifyLikesCount = { modifyLikesCount(it) },
                gotoNextArticle = { onToOtherScreenClicked() },
                shareBtn = { onToShareClicked() }
            )
        }
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

    // Bundle manipulation
    override fun onSaveInstanceState(outState: Bundle) {
        Log.d("MainActivity", "onSaveInstanceState")
        super.onSaveInstanceState(outState)
        outState.putInt(LIKES_COUNT, likesCount)
        outState.putBoolean(SECOND_ARTICLE_IS_READ, secondArticleIsRead)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Log.d("MainActivity", "onRestoreInstanceState")
        super.onRestoreInstanceState(savedInstanceState)
        likesCount = savedInstanceState.getInt(LIKES_COUNT)
        secondArticleIsRead = savedInstanceState.getBoolean(SECOND_ARTICLE_IS_READ)
    }

    fun onToShareClicked() {
        Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, getString(R.string.first_articale_text))
            startActivity(Intent.createChooser(this, "Share"))
        }
    }

    private fun modifyLikesCount(modify: Int): () -> Unit {
        return {likesCount += modify}
    }

    private fun onToOtherScreenClicked() {
        Log.d("MainActivity", "goto next scree")
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(SECOND_ARTICLE_IS_READ, secondArticleIsRead)
        resultLauncher.launch(intent)
    }

    // Companion object
    companion object {
        const val LIKES_COUNT = "LIKES_COUNT"
        const val SECOND_ARTICLE_IS_READ = "SECOND_ARTICLE_IS_READ"
    }
}