package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import ru.urfu.droidpractice1.SecondActivity.Companion.KEY_READ
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {
    private var read by mutableStateOf(false)
    private var likeCount by mutableStateOf(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityScreen(
                onToOtherScreenClicked = ::navigateToSecondActivity,
                onToShareClicked = ::shareArticle,
                onLike = ::incrementLikeCount,
                onDislike = ::decrementLikeCount,
                read = read,
                likeCount = likeCount
            )
        }
    }

    // Handle activity result to get updated 'read' status
    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            read = result.data?.getBooleanExtra(KEY_READ, false) ?: false
        }
    }

    private fun shareArticle() {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "На фоне серьёзного внутреннего конфликта Уругвай разочаровал в Лиме, уступив Перу. Уругвай не смог наладить взаимодействие между линиями, что привело к ошибкам в обороне.")
        }
        startActivity(Intent.createChooser(shareIntent, "Переслать статью"))
    }

    private fun navigateToSecondActivity() {
        val intent = Intent(this, SecondActivity::class.java).apply {
            putExtra(KEY_READ, read)
        }
        resultLauncher.launch(intent)
    }

    private fun incrementLikeCount() {
        likeCount++
    }

    private fun decrementLikeCount() {
        if (likeCount > 0) {
            likeCount--
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("count", likeCount)
        outState.putBoolean("read", read)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        likeCount = savedInstanceState.getInt("count", 0)
        read = savedInstanceState.getBoolean("read", false)
    }
}
